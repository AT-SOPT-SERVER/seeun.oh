package org.sopt.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.exception.ErrorCode;
import org.sopt.exception.SuccessCode;

public class ApiResponse<T> {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    private ApiResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> success(SuccessCode successCode) {
        return new ApiResponse<>(
                successCode.getStatus().value(),
                successCode.getMessage(),
                null
        );
    }

    public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
        return new ApiResponse<>(
                successCode.getStatus().value(),
                successCode.getMessage(),
                data
        );
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(
                errorCode.getStatus().value(),
                errorCode.getMessage(),
                null
        );
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
