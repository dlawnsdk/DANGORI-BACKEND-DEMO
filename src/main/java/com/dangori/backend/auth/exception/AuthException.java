package com.dangori.backend.auth.exception;

import com.dangori.backend.common.exception.BaseExceptionType;
import com.dangori.backend.common.exception.BaseException;

public class AuthException extends BaseException {

    private final AuthExceptionType exceptionType;

    public AuthException(AuthExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }


    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}