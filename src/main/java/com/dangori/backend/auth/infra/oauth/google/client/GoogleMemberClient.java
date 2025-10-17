package com.dangori.backend.auth.infra.oauth.google.client;

import com.dangori.backend.auth.domain.OAuth2MemberClient;
import com.dangori.backend.auth.domain.OAuth2ServerType;
import com.dangori.backend.auth.exception.AuthException;
import com.dangori.backend.auth.exception.AuthExceptionType;
import com.dangori.backend.auth.infra.oauth.google.dto.GoogleMemberResponse;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.domain.CommonCode;
import com.dangori.backend.common.domain.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OAuth2MemberClient {

    private final GoogleApiClient googleApiClient;
    private final CommonCodeRepository commonCodeRepository;

    @Override
    public OAuth2ServerType supportServer() {
        return OAuth2ServerType.GOOGLE;
    }

    @Override
    public UserInfo fetch(String accessToken) {
        GoogleMemberResponse response = googleApiClient.fetchMember("Bearer " + accessToken);

        Long joinTypeCodeSeq = commonCodeRepository.findByCodeName("GOOGLE")
                .orElseThrow(() -> new AuthException(AuthExceptionType.INVALID_OAUTH_TYPE_CODE)).getSeq();

        return response.toDomain(joinTypeCodeSeq);
    }
}
