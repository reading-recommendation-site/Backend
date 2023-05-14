package com.suggestion.book.domain.recommendation.entity;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

//@RedisHash("Bestseller")
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Bestseller {
//    @Id
//    private String id;
//    private BestSellerListResponseDto bestSellerListResponseDto;
//
//    @Builder
//    public Bestseller(String id, BestSellerListResponseDto bestSellerListResponseDto) {
//        this.id = id;
//        this.bestSellerListResponseDto = bestSellerListResponseDto;
//    }
//}
