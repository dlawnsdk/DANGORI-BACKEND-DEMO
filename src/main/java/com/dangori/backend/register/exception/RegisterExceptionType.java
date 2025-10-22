package com.dangori.backend.register.exception;

import com.dangori.backend.common.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
public enum RegisterExceptionType implements BaseExceptionType {

    DUPLICATE_ACCOUNT(ALREADY_REPORTED, "이미 가입된 정보입니다. 다시 시도해주세요"),
    DUPLICATE_EMAIL(ALREADY_REPORTED, "이미 가입된 이메일입니다."),
    DUPLICATE_NUMBER(ALREADY_REPORTED, "이미 가입한 전화번호입니다.");

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
