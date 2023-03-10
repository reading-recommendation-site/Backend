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

    public void updateContents(ContentsRequestDto contentsRequestDto){
        this.contents = contentsRequestDto.getContents();
    }
}
