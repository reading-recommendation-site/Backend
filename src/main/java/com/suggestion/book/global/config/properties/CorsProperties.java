package com.suggestion.book.global.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private final String allowedOrigins;
    private final String allowedMethods;
    private final String allowedHeaders;
    private final Long maxAge;
}
