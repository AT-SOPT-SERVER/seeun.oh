package org.sopt.dto.post.res;

public record PostSearchResponse(
        Long contentId,
        String title,
        String tag
) {
    public static PostSearchResponse of(final Long contentId, final String title, final String tag) {
        return new PostSearchResponse(contentId, title, tag);
    }
}
