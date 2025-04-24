package org.sopt.global.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "존재하지 않는 게시글 ID 입니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "제목은 비어있을 수 없습니다."),
    OVER_LENGTH_TITLE(HttpStatus.BAD_REQUEST,"제목은 30자 이하로 작성해 주세요."),
    DUPLICATE_TITLE(HttpStatus.BAD_REQUEST,"이미 존재하는 제목입니다. 제목은 중복될 수 없습니다."),
    POST_DURATION(HttpStatus.BAD_REQUEST,"게시글은 3분에 1개만 작성할 수 있습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
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
