package org.sopt.dto;

public record PostUpdateResponse(
        Long contentId,
        String title
) {
    public static  PostUpdateResponse of(final Long contentId, final String title) {
        return new PostUpdateResponse(contentId, title);
    }
}
