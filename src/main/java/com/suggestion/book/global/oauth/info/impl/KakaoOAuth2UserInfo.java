package com.suggestion.book.global.oauth.info.impl;

import com.suggestion.book.global.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        if(attributes.containsKey("kakao_account")) {
            Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
            if(kakao_account.containsKey("profile")) {
                Map<String, Object>  profile = (Map<String, Object>) kakao_account.get("profile");
                if(profile.containsKey("nickname")) {
                    return (String) profile.get("nickname");
                }
            }
        }
        return null;
    }

    @Override
    public String getEmail() {
        Map<String,Object> kakaoAccount = (Map<String,Object>) attributes.get("kakao_account");

        if (kakaoAccount == null) {
            return null;
        }
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        if(attributes.containsKey("kakao_account")) {
            Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
            if(kakao_account.containsKey("profile")) {
                Map<String, Object>  profile = (Map<String, Object>) kakao_account.get("profile");
                if(profile.containsKey("profile_image_url")) {
                    return (String) profile.get("profile_image_url");
                }
            }
        }
        return null;
    }
}