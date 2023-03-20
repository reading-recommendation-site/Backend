package com.suggestion.book.global.config;

import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ApiProperties apiProperties;


    /**
     * 네이버 WebClient
     */
    @Bean
    public WebClient naverWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(apiProperties.getNaver().getUri())
                .defaultHeader("X-Naver-Client-Id", apiProperties.getNaver().getNaverClientId())
                .defaultHeader("X-Naver-Client-Secret", apiProperties.getNaver().getNaverClientSecret())
                .build();
    }


    /**
     * 알라딘 WebClient
     */
    @Bean
    public WebClient aladinWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(apiProperties.getAladin().getUri())
                .build();
    }

    /**
     * 도서관 정보 나루 WebClient
     */
    @Bean
    public WebClient data4libraryWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(apiProperties.getNaru().getUri())
                .build();
    }
}
