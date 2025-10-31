package com.dangori.backend.register.guest.exception;


import com.dangori.backend.common.exception.BaseException;
import com.dangori.backend.common.exception.BaseExceptionType;

public class RegisterException extends BaseException {
    private final RegisterExceptionType exceptionType;

    public RegisterException(RegisterExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
