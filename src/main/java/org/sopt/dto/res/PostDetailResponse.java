package org.sopt.dto.res;

public record PostDetailResponse(
        Long contentId,
        String title
) {
    public static  PostDetailResponse of(final Long contentId, final String title) {
        return new PostDetailResponse(contentId, title);
    }
}
