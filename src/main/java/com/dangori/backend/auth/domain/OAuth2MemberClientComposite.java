package com.dangori.backend.auth.domain;

import com.dangori.backend.user.domain.UserInfo;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OAuth2MemberClientComposite {

    private final Map<OAuth2ServerType, OAuth2MemberClient> clients;

    public OAuth2MemberClientComposite(Set<OAuth2MemberClient> clients) {
        this.clients = clients.stream()
                .collect(toMap(OAuth2MemberClient::supportServer, identity()));
    }

    public UserInfo fetch(OAuth2ServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).fetch(authCode);
    }


    private OAuth2MemberClient getClient(OAuth2ServerType oauthServerType) {
        return Optional.ofNullable(clients.get(oauthServerType))
                .orElseThrow();
    }
}
