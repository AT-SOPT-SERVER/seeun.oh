package org.sopt.validation;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.exception.CommonException.*;
import static org.sopt.util.GraphemeUtils.getLengthOfEmojiContainableText;

public class PostValidator {
    private static final int MAX_TITLE_LENGTH = 30;
    private static final long COOL_TIME = 3;

    public static void validateTitleLength(String title) {
        int count = getLengthOfEmojiContainableText(title);
        //30자 초과 검증
        if(count > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(OVER_LENGTH_TITLE.getMessage());
        }
        //입력이 비어있는 경우(공백) 검증
        if(title.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        }
    }

    public static void validateCoolTime(LocalDateTime lastPostTime) {
        if (lastPostTime != null) {
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(lastPostTime, now);
            if (duration.toMinutes() < COOL_TIME) {
                throw new IllegalStateException(POST_DURATION.getMessage());
            }
        }
    }

}
