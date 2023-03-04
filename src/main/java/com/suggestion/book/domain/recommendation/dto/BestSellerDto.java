package com.suggestion.book.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BestSellerDto {
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
