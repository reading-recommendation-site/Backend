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
    private final Aladin aladin;
    private final Naru naru;

    @Getter
    @RequiredArgsConstructor
    public static class Naver {
        private final String uri;
        private final String naverClientId;
        private final String naverClientSecret;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Aladin {
        private final String uri;
        private final String ttbKey;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Naru {
        private final String uri;
        private final String authKey;
    }
}
