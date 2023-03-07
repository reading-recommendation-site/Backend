package com.suggestion.book.domain.community.entity;

import com.suggestion.book.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    private Long no;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "contents")
    private String contents;

    @Column(name = "grade")
    private int grade;

    @Builder
    public Review(Member member, String isbn, String contents, int grade) {
        this.member = member;
        this.isbn = isbn;
        this.contents = contents;
        this.grade = grade;
    }
}
