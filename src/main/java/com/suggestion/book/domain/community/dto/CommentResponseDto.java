package com.suggestion.book.domain.community.dto;

import com.suggestion.book.domain.community.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {
    public Long no;
    public String memberNickname;
    public String contents;
    public LocalDateTime createdAt;

    public static CommentResponseDto from(Comment comment){
        return CommentResponseDto.builder()
                .no(comment.getNo())
                .memberNickname(comment.getMember().getNickname())
                .contents(comment.getContents())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
