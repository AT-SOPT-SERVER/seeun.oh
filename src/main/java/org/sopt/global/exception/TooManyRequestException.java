package org.sopt.global.exception;

import org.sopt.global.common.ErrorCode;

public class TooManyRequestException extends CustomException {

    public TooManyRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
