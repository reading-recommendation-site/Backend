package com.suggestion.book.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetailBookResponseDto {

    public List<DetailBook> item;

    public static class DetailBook {
        public String title;
        public String link;
        public String author;
        public String pubDate;
        public String description;
        public String isbn13;
        public String priceStandard;
        public String cover;
        public String publisher;
        public String categoryName;
        public Info subInfo;

        public static class Info {
            public String subTitle;
            public String originalTitle;
            public List<String> cardReviewImgList;
            public Rating ratingInfo;

            public static class Rating {
                public String ratingScore;
                public String ratingCount;
                public String commentReviewCount;
                public String myReviewCount;
            }
        }
    }
}
