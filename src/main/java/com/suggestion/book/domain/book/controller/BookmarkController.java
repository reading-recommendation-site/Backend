package com.suggestion.book.domain.book.controller;

import com.suggestion.book.domain.book.dto.BookmarkCountResponseDto;
import com.suggestion.book.domain.book.dto.BookmarkResponseDto;
import com.suggestion.book.domain.book.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping(path = "/search/book/{isbn}/bookmark")
    public BookmarkCountResponseDto getBookmark(@PathVariable String isbn,
                                                @AuthenticationPrincipal User principal) {
        return bookmarkService.getBookmarkCountByReview(isbn, Optional.ofNullable(principal));
    }

    @PostMapping(path = "/search/book/{isbn}/bookmark")
    public ResponseEntity<String> createBookmark(@PathVariable String isbn, @AuthenticationPrincipal User principal) {
        bookmarkService.createBookmark(isbn, principal.getUsername());
        return ResponseEntity.ok("북마크 생성");
    }

    @DeleteMapping(path = "/search/book/bookmark/{no}")
    public ResponseEntity<String> deleteBookmark(@PathVariable Long no, @AuthenticationPrincipal User principal) {
        bookmarkService.deleteBookmark(no, principal.getUsername());
        return ResponseEntity.ok("북마크 삭제");
    }

    @GetMapping(path = "/member/bookmark")
    public Page<BookmarkResponseDto> getBookmarkByMember(@PageableDefault(size=5, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                         @AuthenticationPrincipal User principal) {
        return bookmarkService.getBookmarkByMember(pageable, principal.getUsername());
    }
}
