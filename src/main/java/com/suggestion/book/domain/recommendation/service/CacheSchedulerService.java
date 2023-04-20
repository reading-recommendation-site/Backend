package com.suggestion.book.domain.recommendation.service;

import com.suggestion.book.domain.model.PopularBookClassification;
import com.suggestion.book.domain.recommendation.dto.PopularBookConditionsRequestDto;
import com.suggestion.book.domain.recommendation.dto.PopularBookListResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import com.suggestion.book.global.utils.MultiValueMapConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class CacheSchedulerService {

    private final WebClient data4libraryWebClientApi;
    private final PopularBookSaveService popularBookSaveService;
    private final ApiProperties apiProperties;
    private static final String POPULAR_BOOK_URI = "/loanItemSrch";

    /**
     * 24시 마다 redis 에 인기 도서를 저장 합니다.
     */
    @Scheduled(cron = "0 0 24 * * *")
    public void scheduleFixedRateWithInitialDelayTask() {
        String lastMonthDate = getLastMonthDate();
        for (PopularBookClassification p : PopularBookClassification.values()) {
            PopularBookConditionsRequestDto conditionsDto = new PopularBookConditionsRequestDto(p);
            conditionsDto.setStartDt(lastMonthDate);
            MultiValueMap<String, String> params = MultiValueMapConverterUtil.convert(conditionsDto);
            data4libraryWebClientApi
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(POPULAR_BOOK_URI)
                            .queryParam("authKey", apiProperties.getNaru().getAuthKey())
                            .queryParams(params)
                            .queryParam("format", "json")
                            .build())
                    .retrieve()
                    .bodyToFlux(PopularBookListResponseDto.class)
                    .subscribe(e -> popularBookSaveService.popularBookSave(p.name(), e));
        }
    }

    private String getLastMonthDate(){
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.add(Calendar.MONTH, -1); //한달 전
        return sdf.format(cal.getTime());
    }
}
