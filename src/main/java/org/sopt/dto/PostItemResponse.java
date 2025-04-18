package org.sopt.dto;

public record PostItemResponse(
        Long contentId,
        String title
) {
    public static PostItemResponse of(final Long contentId, final String title) {
        return new PostItemResponse(contentId, title);
    }
}
