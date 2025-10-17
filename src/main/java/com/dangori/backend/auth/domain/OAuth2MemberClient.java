package com.dangori.backend.auth.domain;


import com.dangori.backend.user.domain.UserInfo;

public interface OAuth2MemberClient {
    OAuth2ServerType supportServer();

    UserInfo fetch(String accessToken);
}
