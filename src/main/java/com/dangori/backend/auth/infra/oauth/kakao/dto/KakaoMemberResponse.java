package com.dangori.backend.auth.infra.oauth.kakao.dto;

import com.dangori.backend.user.domain.UserDetailInfo;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.enums.YnType;
import com.dangori.backend.common.util.UUIDGenerator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;


@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoMemberResponse(
        Long id,
        boolean hasSignedUp,
        LocalDateTime connectedAt,
        KakaoAccount kakaoAccount
) {

    public UserInfo toDomain(Long joinTypeCode) {
        UserDetailInfo userDetailInfo = UserDetailInfo.builder()
                .nickname(UUIDGenerator.generate(kakaoAccount.profile.nickname))
                .build();

        return UserInfo.builder()
                .userEmail(kakaoAccount.email)
                .registerCode(joinTypeCode)
                .detail(userDetailInfo)
                .banFlag(YnType.N)
                .withdrawFlag(YnType.N)
                .build();
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoAccount(
            boolean profileNeedsAgreement,
            boolean profileNicknameNeedsAgreement,
            boolean profileImageNeedsAgreement,
            Profile profile,
            boolean nameNeedsAgreement,
            String name,
            boolean emailNeedsAgreement,
            boolean isEmailValid,
            boolean isEmailVerified,
            String email,
            boolean ageRangeNeedsAgreement,
            String ageRange,
            boolean birthyearNeedsAgreement,
            String birthyear,
            boolean birthdayNeedsAgreement,
            String birthday,
            String birthdayType,
            boolean genderNeedsAgreement,
            String gender,
            boolean phoneNumberNeedsAgreement,
            String phoneNumber,
            boolean ciNeedsAgreement,
            String ci,
            LocalDateTime ciAuthenticatedAt
    ) {
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Profile(
            String nickname,
            String thumbnailImageUrl,
            String profileImageUrl,
            boolean isDefaultImage
    ) {
    }
}