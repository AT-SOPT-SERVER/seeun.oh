package org.sopt.dto.post.res;

public record PostDetailResponse(
        Long contentId,
        String title,
        String content,
        String nickname,
        String tag
) {
    public static  PostDetailResponse of(final Long contentId, final String title, final String content, final String nickname, final String tag) {
        return new PostDetailResponse(contentId, title, content, nickname, tag);
    }
}
