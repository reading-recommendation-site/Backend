package com.suggestion.book.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member_refresh_token")
public class MemberRefreshToken {

    @JsonIgnore
    @Id
    @Column(name = "refresh_token_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "refresh_token")
    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public MemberRefreshToken(String userId, String refreshToken) {
        this.memberId = userId;
        this.refreshToken = refreshToken;
    }
}
