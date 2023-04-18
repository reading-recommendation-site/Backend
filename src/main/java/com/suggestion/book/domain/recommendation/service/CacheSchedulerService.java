package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import com.suggestion.book.domain.recommendation.entity.PopularBook;
import com.suggestion.book.domain.recommendation.repository.PopularBookRedisRepository;
import com.suggestion.book.global.config.properties.ApiProperties;
import com.suggestion.book.global.utils.MultiValueMapConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class CacheSchedulerService {

    private final WebClient data4libraryWebClientApi;
    private final PopularBookRedisRepository popularBookRedisRepository;
    private final ApiProperties apiProperties;
    private static final String POPULAR_BOOK_URI = "/loanItemSrch";

    @Transactional
    @Scheduled(fixedDelay = 100000, initialDelay = 5000)
    public void scheduleFixedRateWithInitialDelayTask() {
        String lastMonthDate = getLastMonthDate();

        for (PopularBookClassification p : PopularBookClassification.values()) {
            PopularBookConditionsRequestDto conditionsDto = new PopularBookConditionsRequestDto(p);
            conditionsDto.setStartDt(lastMonthDate);
            MultiValueMap<String, String> params = MultiValueMapConverterUtil.convert(conditionsDto);
            Mono<PopularBookListResponseDto> popularBookListResponseDtoMono = data4libraryWebClientApi
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(POPULAR_BOOK_URI)
                            .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                            .queryParams(params)
                            .queryParam("format", "json")
                            .build())
                    .retrieve()
                    .bodyToMono(PopularBookListResponseDto.class);
            PopularBookListResponseDto block = popularBookListResponseDtoMono.block();
            popularBookRedisRepository.save(PopularBook.builder()
                    .id(p.name())
                    .popularBookListResponseDto(block)
                    .build()
            );
        }

        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task with one second initial delay -{}"+ now);
    }

    private String getLastMonthDate(){
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.add(Calendar.MONTH, -1); //한달 전
        return sdf.format(cal.getTime());
    }
}
