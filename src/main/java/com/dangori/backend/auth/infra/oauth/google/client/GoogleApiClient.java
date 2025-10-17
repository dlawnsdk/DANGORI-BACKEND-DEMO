package com.dangori.backend.auth.infra.oauth.google.client;

import com.dangori.backend.auth.infra.oauth.google.dto.GoogleMemberResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public interface GoogleApiClient {

    @GetExchange("https://www.googleapis.com/oauth2/v2/userinfo")
    GoogleMemberResponse fetchMember(@RequestHeader(AUTHORIZATION) String bearerToken);

}
