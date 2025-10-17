package com.dangori.backend.user.exception;

import com.dangori.backend.common.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
public enum UserExceptionType implements BaseExceptionType {


    NOT_FOUND_MEMBER(NOT_FOUND, "회원을 찾을 수 없습니다"),
    NOT_FOUND_USER_PREFER(NOT_FOUND, "선호도 정보가 없습니다"),
    NOT_FOUND_USER_DETAIL(NOT_FOUND, "상세 정보가 없습니다."),
    NOT_FOUND_NATION(NOT_FOUND, "국가를 찾을 수 없습니다."),
    NOT_FOUND_GENDER(NOT_FOUND, "성별을 찾을 수 없습니다."),
    ;

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
