package com.suggestion.book.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PopularBookListResponseDto {
    public PopularBookListResponse response;

    public static class PopularBookListResponse {
        public int resultNum;
        public List<PopularBookDto> docs;
    }
}
