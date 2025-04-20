package org.sopt.exception;

public class PostNotFoundException extends CustomException {
    private final ErrorCode errorCode;

    public PostNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
