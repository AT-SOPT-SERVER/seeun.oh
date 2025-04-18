package org.sopt.dto;

import java.util.List;

public record PostListResponse(
        List<PostItemResponse> contentList
) {
    public static PostListResponse of(final List<PostItemResponse> contentList) {
        return new PostListResponse(contentList);
    }
}
