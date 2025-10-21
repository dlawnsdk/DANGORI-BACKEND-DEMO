package com.dangori.backend.auth.presentation;

import com.dangori.backend.auth.annotation.Auth;
import com.dangori.backend.auth.dto.OAuth2LoginRequest;
import com.dangori.backend.auth.dto.OAuth2LoginResponse;
import com.dangori.backend.auth.dto.RefreshTokenRequest;
import com.dangori.backend.auth.dto.RefreshTokenResponse;
import com.dangori.backend.auth.service.OAuth2Service;
import com.dangori.backend.common.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class OAuth2ApiController {

    private final OAuth2Service oAuth2Service;

    @PostMapping("/login/{provider}")
    public ResultResponse<OAuth2LoginResponse> login(
            @PathVariable String provider,
            @RequestBody OAuth2LoginRequest loginRequest
    ) {
        OAuth2LoginResponse oAuth2LoginResponse = oAuth2Service.login(provider, loginRequest);
        return ResultResponse.success(oAuth2LoginResponse);
    }

    @PostMapping("/refresh")
    public ResultResponse<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String accessToken = oAuth2Service.refresh(request.getRefreshToken());
        return ResultResponse.success(new RefreshTokenResponse(accessToken));
    }

    @DeleteMapping("/logout")
    public ResultResponse<Void> logout(@Auth Long userId) {
        oAuth2Service.logout(userId);


        return ResultResponse.success();
    }
}

