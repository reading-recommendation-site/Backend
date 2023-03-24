package com.suggestion.book.domain.book.service;

import com.suggestion.book.domain.book.dto.BookListResponseDto;
import com.suggestion.book.domain.recommendation.dto.DetailBookResponseDto;
import com.suggestion.book.global.config.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookSearchService {

    private final WebClient naverWebClientApi;
    private final WebClient aladinWebClientApi;
    private final ApiProperties apiProperties;

    public static final String BOOK_TITLE_URI = "/search/book_adv.json";
    public static final String BOOK_ISBN_URI = "/ItemLookUp.aspx";

    public Mono<BookListResponseDto> searchByBookTitle(String title, int start) {
        return naverWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_TITLE_URI)
                        .queryParam("d_titl", title)
                        .queryParam("start", start)
                        .build())
                .retrieve()
                .bodyToMono(BookListResponseDto.class);
    }

    public Mono<DetailBookResponseDto> searchByBookISBN(String isbn) {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_ISBN_URI)
                        .queryParam("ttbkey", apiProperties.getAladin().getTtbKey())
                        .queryParam("ItemId", isbn)
                        .queryParam("ItemIdType","ISBN13")
                        .queryParam("Version",20131101)
                        .queryParam("Cover","Big")
                        .queryParam("OptResult","ratingInfo,cardReviewImgList")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(DetailBookResponseDto.class);
    }
}
