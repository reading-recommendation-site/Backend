package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.recommendation.dto.BestSellerListResponseDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import com.suggestion.book.domain.recommendation.entity.PopularBook;
import com.suggestion.book.domain.recommendation.repository.PopularBookRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PopularBookSaveService {
    private final PopularBookRedisRepository popularBookRedisRepository;
    //private final BestsellerRedisRepository bestsellerRedisRepository;

    public void popularBookSave(String key, PopularBookListResponseDto popularBookListResponseDto) {
        popularBookRedisRepository.save(PopularBook.builder()
                .id(key)
                .popularBookListResponseDto(popularBookListResponseDto)
                .build()
        );
    }

//    public void bestsellerSave(String key, BestSellerListResponseDto bestSellerListResponseDto) {
//        bestsellerRedisRepository.save(Bestseller.builder()
//                .id(key)
//                .bestSellerListResponseDto(bestSellerListResponseDto)
//                .build()
//        );
//    }
}
