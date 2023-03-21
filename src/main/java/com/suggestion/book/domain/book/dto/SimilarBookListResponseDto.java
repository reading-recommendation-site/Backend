package com.suggestion.book.domain.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimilarBookListResponseDto {
    public Response response;
    public static class Response {
        public int resultNum;
        public List<SimilarBook> docs;
    }
}
