package com.suggestion.book.global.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AladinBestSellerResponseDto {
    public String title;
    public String itemsPerPage;
    public String searchCategoryName;
    public List<BestSellerDto> item;

    @Getter
    @Setter
    public static class BestSellerDto{
        public String title;
        public String link;
        public String cover;
        public String author;
        public String publisher;
        public String isbn13;
        public String description;
        public String pubDate;
        public int bestRank;
    }
}
