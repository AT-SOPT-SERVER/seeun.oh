package org.sopt.constant;

import org.sopt.global.exception.InvalidRequestException;

import static org.sopt.global.common.ErrorCode.INVALID_TAG;

public enum Tag {
    BACKEND("백엔드"),
    DATABASE( "데이터베이스"),
    INFRA("인프라");

    private final String value;

    Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Tag fromValue(String value) {
        for (Tag tag : Tag.values()) {
            if (tag.getValue().equals(value)) {
                return tag;
            }
        }
        throw new InvalidRequestException(INVALID_TAG);
    }
}
