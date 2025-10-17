package com.dangori.backend.auth.infra.oauth.config;

import com.dangori.backend.auth.infra.oauth.google.client.GoogleApiClient;
import com.dangori.backend.auth.infra.oauth.kakao.client.KakaoApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class OAuth2HttpInterfaceConfig {

    @Bean
    public KakaoApiClient kakaoApiClient() {
        return createHttpInterface(KakaoApiClient.class);
    }

    @Bean
    public GoogleApiClient googleApiClient() {
        return createHttpInterface(GoogleApiClient.class);
    }

    private <T> T createHttpInterface(Class<T> clazz) {
        WebClient webClient = WebClient.create();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory build = HttpServiceProxyFactory
                .builderFor(adapter).build();
        return build.createClient(clazz);
    }
}
