package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.domain.community.dto.ReviewRequestDto;
import com.suggestion.book.domain.community.exception.InvalidISBNException;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final WebClient naverWebClientApi;

    private static final String URI = "/search/book_adv.json";

    public void createReview(ReviewRequestDto reviewRequestDto, String memberName) {
        if(!validateIsbn(reviewRequestDto.isbn)){
            throw new InvalidISBNException("isbn 이 존재하지 않습니다.");
        }
        Member member = memberRepository.findByMemberId(memberName);
        reviewRepository.save(reviewRequestDto.toEntity(member));
    }

    private boolean validateIsbn(String isbn){
        Mono<BookListResponseDto> bookListResponseDtoMono = naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("d_isbn", isbn)
                        .build())
                .retrieve()
                .bodyToMono(BookListResponseDto.class);
        BookListResponseDto bookListResponseDto = bookListResponseDtoMono.block();
        return bookListResponseDto != null && bookListResponseDto.getTotal() == 1;
    }
}
