package org.sopt.common;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    CONTENT_CREATED(HttpStatus.CREATED, "게시글이 작성되었습니다."),
    GET_ALL_CONTENT(HttpStatus.OK, "전체 게시글이 조회되었습니다."),
    UPDATE_CONTENT(HttpStatus.OK, "게시글이 수정되었습니다."),
    GET_DETAIL_CONTENT(HttpStatus.OK, "게시글 상세 조회"),
    DELETE_CONTENT(HttpStatus.OK, "게시글이 삭제되었습니다."),
    SEARCH_KEYWORD(HttpStatus.OK, "키워드로 게시글 검색 성공");

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
