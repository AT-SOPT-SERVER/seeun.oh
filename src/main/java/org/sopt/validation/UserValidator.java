package org.sopt.validation;

import org.sopt.global.exception.InvalidRequestException;

import static org.sopt.global.common.ErrorCode.*;
import static org.sopt.util.GraphemeUtils.getLengthOfEmojiContainableText;

public class UserValidator {
    private static final int MAX_USERNAME_LENGTH = 10;

    public static void validateUsernameLength(String name) {
        int count = getLengthOfEmojiContainableText(name);

        //입력이 비어있는 경우(공백) 검증
        if(name == null || name.trim().isEmpty()) {
            throw new InvalidRequestException(EMPTY_USERNAME);
        }

        //10자 초과 검증
        if(count > MAX_USERNAME_LENGTH) {
            throw new InvalidRequestException(OVER_LENGTH_USERNAME);
        }
    }
}
