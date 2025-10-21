package com.dangori.backend.register.exception;

import com.dangori.backend.common.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
public enum RegisterExceptionType implements BaseExceptionType {

    DUPLICATE_EMAIL(ALREADY_REPORTED, "이미 존재하는 이메일 입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
