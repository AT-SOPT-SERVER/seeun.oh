package org.sopt.validation;

import org.sopt.global.exception.InvalidRequestException;
import org.sopt.global.exception.TooManyRequestException;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.global.common.ErrorCode.EMPTY_TITLE;
import static org.sopt.global.common.ErrorCode.OVER_LENGTH_TITLE;
import static org.sopt.global.common.ErrorCode.POST_DURATION;
import static org.sopt.util.GraphemeUtils.getLengthOfEmojiContainableText;

public class PostValidator {
    private static final int MAX_TITLE_LENGTH = 30;
    private static final long COOL_TIME = 3;
    private static final long MIN_TO_SEC = 60;

    public static void validateTitleLength(String title) {
        int count = getLengthOfEmojiContainableText(title);
        System.out.println("title length(이모지 포함):"+count); //추후 삭제하기

        //입력이 비어있는 경우(공백) 검증
        if(title == null || title.trim().isEmpty()) {
            throw new InvalidRequestException(EMPTY_TITLE);
        }

        //30자 초과 검증
        if(count > MAX_TITLE_LENGTH) {
            throw new InvalidRequestException(OVER_LENGTH_TITLE);
        }
    }

    public static void validateCoolTime(LocalDateTime lastPostTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastPostTime, now);
        long timeLimit = COOL_TIME * MIN_TO_SEC;

        if (duration.toSeconds() < timeLimit) {
            throw new TooManyRequestException(POST_DURATION);
        }
    }

}
