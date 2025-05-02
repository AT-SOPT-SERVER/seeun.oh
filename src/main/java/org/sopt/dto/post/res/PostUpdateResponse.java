package org.sopt.dto.post.res;

public record PostUpdateResponse(
        Long contentId,
        String title,
        String content
) {
    public static  PostUpdateResponse of(final Long contentId, final String title, final String content) {
        return new PostUpdateResponse(contentId, title, content);
    }
}
