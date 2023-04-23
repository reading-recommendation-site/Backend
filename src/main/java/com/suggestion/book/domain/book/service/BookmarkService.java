package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookmarkCountResponseDto;
import com.suggestion.book.domain.book.dto.BookmarkResponseDto;
import com.suggestion.book.domain.book.entity.Bookmark;
import com.suggestion.book.domain.book.exception.NotFoundBookmarkException;
import com.suggestion.book.domain.book.repository.BookmarkRepository;
import com.suggestion.book.domain.community.dto.BookISBNResponseDto;
import com.suggestion.book.domain.community.exception.InvalidISBNException;
import com.suggestion.book.domain.community.exception.MemberIdMismatchException;
import com.suggestion.book.domain.community.exception.MemberNotFoundException;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final WebClient naverWebClientApi;

    private static final String URI = "/search/book_adv.json";

    @Transactional
    public void createBookmark(String isbn, String memberId) {
        BookISBNResponseDto bookDataByISBN = getBookDataByISBN(isbn);
        if(bookDataByISBN.getTotal() != 1){
            throw new InvalidISBNException("isbn 이 존재 하지 않습니다.");
        }
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));

        if(bookmarkRepository.findByMemberAndIsbn(member, isbn).isEmpty()){
            bookmarkRepository.save(
                    Bookmark.builder()
                            .member(member)
                            .isbn(isbn)
                            .bookISBNResponseDto(bookDataByISBN)
                            .build()
            );
        }
    }

    public BookmarkCountResponseDto getBookmarkCountByReview(String isbn, Optional<User> principalOpt) {

        List<Bookmark> bookmark = bookmarkRepository.findAllByIsbn(isbn);
        if(principalOpt.isPresent()){
            Member member = memberRepository.findByMemberId(principalOpt.get().getUsername())
                    .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
            List<Bookmark> bookmarkByMember = bookmark.stream()
                    .filter(b -> member.getMemberId().equals(b.getMember().getMemberId())).collect(Collectors.toList());
            return BookmarkCountResponseDto.from(bookmark, bookmarkByMember);
        }
        return BookmarkCountResponseDto.from(bookmark);
    }

    @Transactional
    public void deleteBookmark(Long BookmarkId, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        Bookmark bookmark = bookmarkRepository.findById(BookmarkId)
                .orElseThrow(() -> new NotFoundBookmarkException("북마크가 존재하지 않습니다.")); // 북마크 없는 경우 에러 처리
        if (!member.getMemberId().equals(bookmark.getMember().getMemberId())) {
            throw new MemberIdMismatchException("멤버가 불일치 합니다.");
        }
        bookmarkRepository.delete(bookmark);
    }

    public Page<BookmarkResponseDto> getBookmarkByMember(Pageable pageable, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버가 존재 하지 않습니다."));
        return bookmarkRepository.findAllByMember(pageable, member).map(BookmarkResponseDto::from);
    }

    private BookISBNResponseDto getBookDataByISBN(String isbn){
        Mono<BookISBNResponseDto> bookISBNResponseDtoMono = naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("d_isbn", isbn)
                        .build())
                .retrieve()
                .bodyToMono(BookISBNResponseDto.class);
        return bookISBNResponseDtoMono.block();
    }
}
