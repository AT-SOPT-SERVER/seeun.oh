package org.sopt.exception;

public class PostNotFoundException extends CustomException {

    public PostNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
