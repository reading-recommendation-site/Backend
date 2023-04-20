package com.suggestion.book.domain.recommendation.dto;

import com.suggestion.book.domain.model.PopularBookClassification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PopularBookConditionsRequestDto {
    public String startDt;
    public String endDt;
    public Integer gender;
    public Integer age;
    public Integer region;
    public Integer pageSize;

    public PopularBookConditionsRequestDto(PopularBookClassification popularBookClassification) {
        this.gender = popularBookClassification.getGender();
        this.age = popularBookClassification.getAge();
        this.region = popularBookClassification.getRegin();
        this.pageSize = popularBookClassification.getPageSize();
    }
}
