package com.suggestion.book.domain.recommendation.entity;

import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("PopularBook")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopularBook{

    @Id
    private String id;
    private PopularBookListResponseDto popularBookListResponseDto;

    @Builder
    public PopularBook(String id, PopularBookListResponseDto popularBookListResponseDto) {
        this.id = id;
        this.popularBookListResponseDto = popularBookListResponseDto;
    }
}
