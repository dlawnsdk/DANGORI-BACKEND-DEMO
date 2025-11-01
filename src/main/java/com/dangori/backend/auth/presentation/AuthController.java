package com.dangori.backend.auth.presentation;

import com.dangori.backend.auth.annotation.Auth;
import com.dangori.backend.auth.dto.AuthLoginRequest;
import com.dangori.backend.auth.dto.AuthLoginResponse;
import com.dangori.backend.auth.dto.RefreshTokenRequest;
import com.dangori.backend.auth.dto.RefreshTokenResponse;
import com.dangori.backend.auth.service.AuthService;
import com.dangori.backend.common.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResultResponse<AuthLoginResponse> login(
            @RequestBody AuthLoginRequest loginRequest
    ) {
        AuthLoginResponse authLoginResponse = authService.login(loginRequest);
        return ResultResponse.success(authLoginResponse);
    }

    @PostMapping("/refresh")
    public ResultResponse<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String accessToken = authService.refresh(request.getRefreshToken());
        return ResultResponse.success(new RefreshTokenResponse(accessToken));
    }

    @DeleteMapping("/logout")
    public ResultResponse<Void> logout(@Auth Long userId) {
        authService.logout(userId);


        return ResultResponse.success();
    }
}

