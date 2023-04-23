package com.suggestion.book.domain.book.entity;

import com.suggestion.book.domain.community.dto.BookISBNResponseDto;
import com.suggestion.book.domain.member.entity.Member;
import com.suggestion.book.domain.model.TimestampEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bookmark")
public class Bookmark extends TimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_img_url")
    private String bookImgUrl;

    @Column(name = "isbn")
    private String isbn;

    @Builder
    public Bookmark(Member member, String isbn, BookISBNResponseDto bookISBNResponseDto) {
        this.member = member;
        this.isbn = isbn;
        this.bookTitle = bookISBNResponseDto.getItems().get(0).title;
        this.bookAuthor = bookISBNResponseDto.getItems().get(0).author;;
        this.bookImgUrl = bookISBNResponseDto.getItems().get(0).image;
    }
}
