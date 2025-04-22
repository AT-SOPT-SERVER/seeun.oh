package org.sopt.exception;

public class TooManyRequestException extends CustomException {

    public TooManyRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
