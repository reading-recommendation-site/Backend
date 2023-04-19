package com.suggestion.book.domain.model;

import lombok.Getter;

@Getter
public enum PopularBookClassification {
    //성별
    MAN(null,0,-1,10),
    WOMAN(null, 1, -1, 10),

    //나이
    INFANT(null, 2, 0, 10), //영유아
    CHIDE(null, 2, 6, 10), //유아
    ELEMENTARY(null, 2, 8, 10),
    TEENAGER(null, 2, 14, 10),
    TWENTIES(null, 2, 20, 10),
    THIRTIES(null, 2, 30, 10),
    FORTIES(null, 2, 40, 10),
    FIFTIES(null, 2, 50, 10),
    SIXTIES(null, 2, 60, 10),

    //지역
    SEOUL(11, 2, -1, 10),
    BUSAN(21,2,-1, 10),
    DAEGU(22,2,-1, 10),
    INCHEON(23,2,-1, 10),
    GWANGJ(24,2,-1, 10),
    DAEJEON(25,2,-1, 10),
    ULSAN(26,2,-1, 10),
    SEJONG(29,2,-1, 10),
    GYEONGGI(31,2,-1, 10),
    GANGWON(32,2,-1, 10),
    CHUNGBUK(33,2,-1, 10),
    CHUNGNAM(34,2,-1, 10),
    JEONBUK(35,2,-1, 10),
    JEONNAM(36,2,-1, 10),
    GYEONGBUK(37,2,-1, 10),
    GYEONGNAM(38,2,-1, 10),
    JEJU(39,2,-1, 10);

    private final Integer regin;
    private final Integer gender;
    private final Integer age;
    private final Integer pageSize;

    PopularBookClassification(Integer regin, Integer gender, Integer age, Integer pageSize){
        this.regin = regin;
        this.gender = gender;
        this.age = age;
        this.pageSize = pageSize;
    }
}
