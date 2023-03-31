package com.suggestion.book.domain.community.entity;

import com.suggestion.book.domain.community.dto.ContentsRequestDto;
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
@Table(name = "review")
public class Review extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
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

    @Column(name = "contents")
    private String contents;

    @Column(name = "grade")
    private int grade;

    @Builder
    public Review(Member member, String bookTitle, String bookAuthor, String bookImgUrl, String isbn, String contents, int grade) {
        this.member = member;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookImgUrl = bookImgUrl;
        this.isbn = isbn;
        this.contents = contents;
        this.grade = grade;
    }

    public void updateContents(ContentsRequestDto contentsRequestDto){
        this.contents = contentsRequestDto.getContents();
    }
}
