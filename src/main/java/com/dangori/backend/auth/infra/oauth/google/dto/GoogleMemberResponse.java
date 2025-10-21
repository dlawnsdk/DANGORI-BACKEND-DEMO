package com.dangori.backend.auth.infra.oauth.google.dto;

import com.dangori.backend.user.domain.UserDetailInfo;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.enums.YnType;
import com.dangori.backend.common.util.UUIDGenerator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String id,
        String name,
        String givenName,
        String picture,
        String locale,
        String email
) {

    public UserInfo toDomain(Long joinTypeCode) {

        UserDetailInfo userDetailInfo = UserDetailInfo.builder()
                .profileImage(picture)
                .userName(UUIDGenerator.generate(givenName))
                .build();

        return UserInfo.builder()
                .userEmail(email)
                .registerCode(joinTypeCode)
                .build();
    }
}
