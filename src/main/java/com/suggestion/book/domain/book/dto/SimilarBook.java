package com.suggestion.book.domain.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimilarBook {
    public Book book;
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Book{
        public String no;
        @JsonProperty("bookname")
        public String bookName;
        public String authors;
        public String publisher;
        public String publicationYear;
        public String isbn13;
        public String additionSymbol;
        public String vol;
        public String classNo;
        public String classNm;
        @JsonProperty("bookImageURL")
        public String bookImageURL;
    }
}
