package com.dangori.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuthLoginRequest {

    @JsonProperty("access_token")
    private String accessToken;
    private String provider;
}