package org.sopt.dto.user.res;

import org.sopt.domain.User;

public record UserCreateResponse(
        Long userId,
        String nickname
) {
    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(user.getId(), user.getNickname());
    }
}
