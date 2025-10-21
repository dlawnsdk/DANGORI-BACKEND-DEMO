package com.dangori.backend.common.dto;

import com.dangori.backend.common.enums.ResponseMessage;
import com.dangori.backend.common.exception.BaseExceptionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultResponse<T> {
    private final String message;
    private final Boolean resultFlag;
    private final Integer statusCode;
    private final T data;

    public static <T> ResultResponse<T> success(T data) {
        return ResultResponse.<T>builder()
                .message(ResponseMessage.SUCCESS.message())
                .resultFlag(true)
                .statusCode(ResponseMessage.SUCCESS.httpStatus().value())
                .data(data)
                .build();
    }

    public static <T> ResultResponse<T> fail(BaseExceptionType responseMessage, T data) {
        return ResultResponse.<T>builder()
                .statusCode(responseMessage.httpStatus().value())
                .message(responseMessage.errorMessage())
                .data(data)
                .build();
    }

    public static ResultResponse<Void> success() {
        return ResultResponse.<Void>builder()
                .message(ResponseMessage.SUCCESS.message())
                .resultFlag(true)
                .statusCode(ResponseMessage.SUCCESS.httpStatus().value())
                .data(null)
                .build();
    }

    public static <T> ResultResponse<T> success(String message, T data) {
        return ResultResponse.<T>builder()
                .message(message)
                .resultFlag(true)
                .statusCode(ResponseMessage.SUCCESS.httpStatus().value())
                .data(data)
                .build();
    }

}
