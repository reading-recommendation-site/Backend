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
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_no")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "contents")
    private String contents;

    @Builder
    public Comment(Review review, Member member, String contents) {
        this.review = review;
        this.member = member;
        this.contents = contents;
    }

    public void updateContents(ContentsRequestDto contentsRequestDto){
        this.contents = contentsRequestDto.getContents();
    }
}
