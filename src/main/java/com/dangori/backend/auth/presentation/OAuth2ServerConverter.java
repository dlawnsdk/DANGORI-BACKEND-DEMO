package com.dangori.backend.auth.presentation;

import com.dangori.backend.auth.domain.OAuth2ServerType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ServerConverter implements Converter<String, OAuth2ServerType> {

    @Override
    public OAuth2ServerType convert(String source) {
        return OAuth2ServerType.fromName(source);
    }
}
