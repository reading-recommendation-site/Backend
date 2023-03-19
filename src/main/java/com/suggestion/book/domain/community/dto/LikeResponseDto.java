package com.suggestion.book.domain.community.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponseDto {
    public Long likeNo;
    public int count;
}
