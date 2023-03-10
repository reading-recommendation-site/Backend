package com.suggestion.book.domain.member.entity;

import com.suggestion.book.domain.model.ProviderType;
import com.suggestion.book.domain.model.RoleType;
import com.suggestion.book.domain.model.TimestampEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Builder
    public Member(String memberId, String email, String name, String profileImageUrl,
                  ProviderType providerType, RoleType roleType) {
        this.memberId = memberId;
        this.name = name;
        this.email = email != null ? email : "NO_EMAIL";;
        this.nickname = "test";
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
    }
}