package com.dangori.backend.common.exception;

import com.dangori.backend.auth.exception.AuthException;
import com.dangori.backend.common.dto.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException(AuthException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResultResponse<Boolean>> handleBaseException(BaseException e) {
        BaseExceptionType type = e.exceptionType();

        ResultResponse<Boolean> body = ResultResponse.fail(type, false);

        return ResponseEntity
                .status(type.httpStatus())
                .body(body);
    }
}
