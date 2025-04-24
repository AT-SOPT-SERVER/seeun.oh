package org.sopt.global.exception;

import org.sopt.global.common.ErrorCode;

public class InvalidRequestException extends CustomException {

    public InvalidRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
