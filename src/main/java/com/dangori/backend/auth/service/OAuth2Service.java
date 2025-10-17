package com.dangori.backend.auth.service;

import com.dangori.backend.auth.domain.OAuth2MemberClientComposite;
import com.dangori.backend.auth.domain.OAuth2ServerType;
import com.dangori.backend.auth.domain.RefreshToken;
import com.dangori.backend.auth.domain.repository.RefreshTokenRepository;
import com.dangori.backend.auth.dto.OAuth2LoginRequest;
import com.dangori.backend.auth.dto.OAuth2LoginResponse;
import com.dangori.backend.auth.exception.AuthException;
import com.dangori.backend.auth.exception.AuthExceptionType;
import com.dangori.backend.config.security.provider.JwtTokenProvider;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.user.domain.repository.UserInfoRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OAuth2Service {
    private final OAuth2MemberClientComposite clientComposite;
    private final UserInfoRepository userInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public OAuth2LoginResponse login(String providerName, OAuth2LoginRequest loginRequest) {
        OAuth2ServerType provider = OAuth2ServerType.fromName(providerName);

        UserInfo userInfo = clientComposite.fetch(provider, loginRequest.getAccessToken());

        UserInfo savedUserInfo = userInfoRepository.findByUserEmail(userInfo.getUserEmail())
                .orElseGet(() -> userInfoRepository.save(userInfo));

        Long userId = savedUserInfo.getSeq();

        String accessToken = jwtTokenProvider.createToken(String.valueOf(savedUserInfo.getSeq()));
        String refreshToken = jwtTokenProvider.createRefreshToken(String.valueOf(savedUserInfo.getSeq()));

        refreshTokenRepository.findByUserSeq(userId)
                .ifPresentOrElse(
                        token -> token.updateToken(refreshToken, jwtTokenProvider.getRefreshTokenExpiryDate()),
                        () -> refreshTokenRepository.save(
                                RefreshToken.builder()
                                        .userSeq(userId)
                                        .token(refreshToken)
                                        .expiresAt(jwtTokenProvider.getRefreshTokenExpiryDate())
                                        .build()
                        )
                );

        return new OAuth2LoginResponse(accessToken, refreshToken);
    }

    public String refresh(String refreshToken) {
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);

        String type = claims.get("type", String.class);
        if (!"refresh".equals(type)) {
            throw new AuthException(AuthExceptionType.INVALID_REFRESH_TOKEN);
        }

        Long userSeq = Long.parseLong(claims.getSubject());

        RefreshToken savedToken = refreshTokenRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new AuthException(AuthExceptionType.EXPIRED_REFRESH_TOKEN));

        if (!savedToken.getToken().equals(refreshToken)) {
            throw new AuthException(AuthExceptionType.INVALID_REFRESH_TOKEN);
        }

        if (claims.getExpiration().before(new Date())) {
            throw new AuthException(AuthExceptionType.EXPIRED_REFRESH_TOKEN);
        }


        return jwtTokenProvider.createToken(String.valueOf(savedToken.getSeq()));
    }

    @Transactional
    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserSeq(userId);
    }

}
