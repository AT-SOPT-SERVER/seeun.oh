package org.sopt.global.exception;

import org.sopt.global.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 유저 id 입니다."),
    EMPTY_USERNAME(HttpStatus.BAD_REQUEST, "유저의 이름은 비어있을 수 없습니다."),
    OVER_LENGTH_USERNAME(HttpStatus.BAD_REQUEST,"유저의 이름은 10자 이하로 작성해 주세요."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다. 닉네임은 중복될 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    UserErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
