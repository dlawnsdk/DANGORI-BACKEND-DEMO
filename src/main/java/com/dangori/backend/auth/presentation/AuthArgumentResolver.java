package com.dangori.backend.auth.presentation;

import com.dangori.backend.auth.annotation.Auth;
import com.dangori.backend.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.dangori.backend.auth.exception.AuthExceptionType.UNAUTHORIZED_REQUEST;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthContext authContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class)
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        if (authContext.memberId() == null) {
            throw new AuthException(UNAUTHORIZED_REQUEST);
        }
        return authContext.memberId();
    }
}
