package com.dangori.backend.auth.infra.oauth.kakao.client;

import com.dangori.backend.auth.domain.OAuth2MemberClient;
import com.dangori.backend.auth.domain.OAuth2ServerType;
import com.dangori.backend.auth.exception.AuthException;
import com.dangori.backend.auth.exception.AuthExceptionType;
import com.dangori.backend.auth.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.domain.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuth2MemberClient implements OAuth2MemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final CommonCodeRepository commonCodeRepository;

    @Override
    public OAuth2ServerType supportServer() {
        return OAuth2ServerType.KAKAO;
    }

    @Override
    public UserInfo fetch(String accessToken) {
        KakaoMemberResponse response = kakaoApiClient.fetchMember("Bearer " + accessToken);

        Long joinTypeCodeId = commonCodeRepository.findByCodeName("KAKAO")
                .orElseThrow(() -> new AuthException(AuthExceptionType.INVALID_OAUTH_TYPE_CODE)).getSeq();

        return response.toDomain(joinTypeCodeId);
    }
}
