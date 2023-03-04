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

    @Bean
    public WebClient naverWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(apiProperties.getNaver().getUri())
                .defaultHeader("X-Naver-Client-Id", apiProperties.getNaver().getNaverClientId())
                .defaultHeader("X-Naver-Client-Secret", apiProperties.getNaver().getNaverClientSecret())
                .build();
    }

    @Bean
    public WebClient aladinWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(apiProperties.getAladin().getUri())
                .build();
    }
}
