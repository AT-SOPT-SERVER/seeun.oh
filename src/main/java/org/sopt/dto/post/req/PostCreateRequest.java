package org.sopt.dto.post.req;


public record PostCreateRequest(
        String title,
        String content,
        String tag
) {
}
