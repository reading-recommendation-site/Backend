package com.suggestion.book.domain.community.service;

import com.suggestion.book.domain.community.dto.BookISBNResponseDto;
import com.suggestion.book.domain.community.dto.ContentsRequestDto;
import com.suggestion.book.domain.community.dto.ReviewRequestDto;
import com.suggestion.book.domain.community.dto.ReviewResponseDto;
import com.suggestion.book.domain.community.entity.Review;
import com.suggestion.book.domain.community.exception.InvalidISBNException;
import com.suggestion.book.domain.community.exception.MemberIdMismatchException;
import com.suggestion.book.domain.community.exception.ReviewNotFoundException;
import com.suggestion.book.domain.community.repository.CommentRepository;
import com.suggestion.book.domain.community.repository.LikeRepository;
import com.suggestion.book.domain.community.repository.ReviewRepository;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final WebClient naverWebClientApi;


    private static final String URI = "/search/book_adv.json";

    @Transactional
    public void createReview(ReviewRequestDto reviewRequestDto, String memberId) {
        if(isNotValidIsbn(reviewRequestDto.isbn)){
            throw new InvalidISBNException("isbn 이 존재 하지 않습니다.");
        }
        Member member = memberRepository.findByMemberId(memberId);
        reviewRepository.save(reviewRequestDto.toEntity(member));
    }

    public Page<ReviewResponseDto> getAllReviewList(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewResponseDto::from);
    }

    public Page<ReviewResponseDto> getReviewListByIsbn(Pageable pageable,String isbn) {
        if(isNotValidIsbn(isbn)){
            throw new InvalidISBNException("isbn 이 존재 하지 않습니다.");
        }
        return reviewRepository.findAllByIsbn(pageable,isbn).map(ReviewResponseDto::from);
    }

    public Page<ReviewResponseDto> getReviewListByMember(Pageable pageable, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return reviewRepository.findAllByMember(pageable,member).map(ReviewResponseDto::from);
    }

    @Transactional
    public void updateReview(Long reviewId, ContentsRequestDto contentsRequestDto, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        if(!memberId.equals(review.getMember().getMemberId())){
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        review.updateContents(contentsRequestDto);
    }

    @Transactional
    public void deleteReview(Long reviewId, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        Review review = reviewOpt.orElseThrow(() -> new ReviewNotFoundException("리뷰가 존재 하지 않습니다."));
        if(!memberId.equals(review.getMember().getMemberId())){
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        commentRepository.deleteByReview(review.getNo());
        likeRepository.deleteByReview(review.getNo());
        reviewRepository.delete(review);
    }

    private boolean isNotValidIsbn(String isbn){
        Mono<BookISBNResponseDto> bookISBNResponseDtoMono = naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("d_isbn", isbn)
                        .build())
                .retrieve()
                .bodyToMono(BookISBNResponseDto.class);
        BookISBNResponseDto bookISBNResponseDto = bookISBNResponseDtoMono.block();
        return bookISBNResponseDto == null || bookISBNResponseDto.getTotal() != 1;
    }
}
