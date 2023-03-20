package com.suggestion.book.domain.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryListResponseDto {
    public LibraryList response;

    public static class LibraryList{
        public String pageNo;
        public String pageSize;
        public String numFound;
        public String resultNum;
        public List<LibraryDto> libs;
    }
}
