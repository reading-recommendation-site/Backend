package com.suggestion.book.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BestSellerListResponseDto {
    public String title;
    public String itemsPerPage;
    public String searchCategoryName;
    public List<BestSellerDto> item;
}
