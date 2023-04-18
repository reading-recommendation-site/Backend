package com.suggestion.book.domain.model;

import lombok.Getter;

@Getter
public enum PopularBookClassification {
    //성별
    MAN(null,0,-1,10,1),
    WOMAN(null, 1, -1, 10, 1),

    //나이
    INFANT(null, 2, 0, 10, 1), //영유아
    CHIDE(null, 2, 6, 10, 1), //유아
    ELEMENTARY(null, 2, 8, 10, 1),
    TEENAGER(null, 2, 14, 10, 1),
    TWENTIES(null, 2, 20, 10, 1),
    THIRTIES(null, 2, 30, 10, 1),
    FORTIES(null, 2, 40, 10, 1),
    FIFTIES(null, 2, 50, 10, 1),
    SIXTIES(null, 2, 60, 10, 1),

    //지역
    SEOUL(11, 2, -1, 10, 1),
    BUSAN(21,2,-1, 10,1),
    DAEGU(22,2,-1, 10,1),
    INCHEON(23,2,-1, 10,1),
    GWANGJ(24,2,-1, 10,1),
    DAEJEON(25,2,-1, 10,1),
    ULSAN(26,2,-1, 10,1),
    SEJONG(29,2,-1, 10,1),
    GYEONGGI(31,2,-1, 10,1),
    GANGWON(32,2,-1, 10,1),
    CHUNGBUK(33,2,-1, 10,1),
    CHUNGNAM(34,2,-1, 10,1),
    JEONBUK(35,2,-1, 10,1),
    JEONNAM(36,2,-1, 10,1),
    GYEONGBUK(37,2,-1, 10,1),
    GYEONGNAM(38,2,-1, 10,1),
    JEJU(39,2,-1, 10,1);

    private final Integer regin;
    private final Integer gender;
    private final Integer age;
    private final Integer pageSize;
    private final Integer pageNo;

    PopularBookClassification(Integer regin, Integer gender, Integer age, Integer pageSize, Integer pageNo){
        this.regin = regin;
        this.gender = gender;
        this.age = age;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }
}
