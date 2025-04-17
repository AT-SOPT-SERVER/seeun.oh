package org.sopt.common;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    CONTENT_CREATED(HttpStatus.CREATED, "게시글이 작성되었습니다.");

    private final HttpStatus status;
    private final String message;

    SuccessCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
