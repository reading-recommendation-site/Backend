package com.suggestion.book.global.config;

import com.suggestion.book.domain.member.repository.MemberRefreshTokenRepository;
import com.suggestion.book.domain.model.RoleType;
import com.suggestion.book.global.config.properties.AppProperties;
import com.suggestion.book.global.config.properties.CorsProperties;
import com.suggestion.book.global.oauth.exception.RestAuthenticationEntryPoint;
import com.suggestion.book.global.oauth.filter.TokenAuthenticationFilter;
import com.suggestion.book.global.oauth.hendler.OAuth2AuthenticationFailureHandler;
import com.suggestion.book.global.oauth.hendler.OAuth2AuthenticationSuccessHandler;
import com.suggestion.book.global.oauth.hendler.TokenAccessDeniedHandler;
import com.suggestion.book.global.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.suggestion.book.global.oauth.service.CustomOAuth2UserService;
import com.suggestion.book.global.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()// h2 console ????????? ??????
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling() //????????????
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()) //???????????? ?????? ???????????? ???????????? ???????????? ?????? ??????.
                .accessDeniedHandler(tokenAccessDeniedHandler) //????????? ?????? ???????????? ???????????? ???????????? ????????????.
                .and()
                .authorizeRequests() //?????? ??????
                .antMatchers("/h2-console/**").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
                .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
                .antMatchers("/search/*").permitAll()
                .antMatchers("/recommendation/*").permitAll()
                .anyRequest().authenticated() //?????? ???????????? ????????? ????????? ??????.
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization") //????????? url ??? ????????? oauth ???????????? ??????
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/*/oauth2/code/*")
                .and()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler());

        //????????? ?????? ??????
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*
     * security ?????? ???, ????????? ????????? ??????
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * ?????? ?????? ??????
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * ?????? ?????? ?????? Repository
     * ?????? ????????? ?????? ?????? ????????? ??? ??????.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                memberRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
     * Cors ??????
     * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}
