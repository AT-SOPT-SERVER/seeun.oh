package org.sopt.exception;

public class InvalidRequestException extends CustomException {
    private final ErrorCode errorCode;

    public InvalidRequestException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
