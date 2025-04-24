package org.sopt.global.exception;

import org.sopt.global.common.ErrorCode;

public class PostNotFoundException extends CustomException {

    public PostNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
