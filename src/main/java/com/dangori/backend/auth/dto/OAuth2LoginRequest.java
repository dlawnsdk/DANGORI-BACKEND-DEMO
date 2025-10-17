package com.dangori.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OAuth2LoginRequest {

    @JsonProperty("access_token")
    private String accessToken;
}