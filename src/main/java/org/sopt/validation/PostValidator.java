package org.sopt.validation;

import org.sopt.exception.InvalidRequestException;
import org.sopt.exception.TooManyRequestException;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.exception.ErrorCode.EMPTY_TITLE;
import static org.sopt.exception.ErrorCode.OVER_LENGTH_TITLE;
import static org.sopt.exception.ErrorCode.POST_DURATION;
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
