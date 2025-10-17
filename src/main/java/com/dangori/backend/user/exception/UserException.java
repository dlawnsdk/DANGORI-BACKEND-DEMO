package com.dangori.backend.user.exception;

import com.dangori.backend.common.exception.BaseException;

public class UserException extends BaseException {

    private final UserExceptionType exceptionType;

    public UserException(UserExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }

    @Override
    public UserExceptionType exceptionType() {
        return exceptionType;
    }
}