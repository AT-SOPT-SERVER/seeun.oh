package org.sopt.exception;

public class TooManyRequestException extends CustomException {
    private final ErrorCode errorCode;

    public TooManyRequestException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
