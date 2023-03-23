package com.suggestion.book.domain.recommendation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularBookDto {
    public PopularBook doc;

    public static class PopularBook {
        public int ranking;
        @JsonProperty("bookname")
        public String bookName;
        public String authors;
        public String publisher;
        @JsonProperty("publication_year")
        public String publicationYear;
        public String isbn13;
        public String bookImageURL;
    }
}
