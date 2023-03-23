package com.suggestion.book.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularBookConditionsRequestDto {
    public String startDt;
    public String endDt;
    public Integer gender;
    public Integer from_age;
    public Integer to_age;
    public Integer region;
    public Integer pageSize;
}
