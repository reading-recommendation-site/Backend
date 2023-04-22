package com.suggestion.book.domain.book.entity;

import com.suggestion.book.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class Bookmark {
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
}
