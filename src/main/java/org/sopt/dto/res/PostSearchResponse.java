package org.sopt.dto.res;

public record PostSearchResponse(
        Long contentId,
        String title
) {
    public static PostSearchResponse of(final Long contentId, final String title) {
        return new PostSearchResponse(contentId, title);
    }
}
