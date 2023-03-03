package com.suggestion.book.global.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private final Naver naver;

    @Getter
    @RequiredArgsConstructor
    public static class Naver {
        private final String url;
        private final String naverClientId;
        private final String naverClientSecret;
    }
}
