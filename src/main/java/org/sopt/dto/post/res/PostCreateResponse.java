package org.sopt.dto.post.res;

import org.sopt.domain.Post;

public record PostCreateResponse(
        Long contentId
) {
    public static PostCreateResponse from(Post post) {
        return new PostCreateResponse(post.getId());
    }
}
