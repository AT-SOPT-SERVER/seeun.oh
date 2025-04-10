package org.sopt.exception;

public enum CommonException {
    EMPTY_TITLE("❌ 제목은 비어있을 수 없습니다."),
    OVER_LENGTH_TITLE("❌ 제목은 30자 미만이어야 합니다."),
    DUPLICATE_TITLE("❌ 이미 존재하는 제목입니다. 제목은 중복될 수 없습니다."),
    POST_DURATION("❌ 게시글은 3분에 1개만 작성할 수 있습니다.");

    private final String message;

    CommonException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
