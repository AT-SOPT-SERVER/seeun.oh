package org.sopt.dto.post.res;

public record PostItemResponse(
        Long contentId,
        String title,
        String nickname
) {
    public static PostItemResponse of(final Long contentId, final String title, final String nickname) {
        return new PostItemResponse(contentId, title, nickname
        );
    }
}
