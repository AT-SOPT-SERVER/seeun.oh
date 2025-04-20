package org.sopt.dto.res;

import java.util.List;

public record PostItemListResponse(
        List<PostItemResponse> contentList
) {
    public static PostItemListResponse of(final List<PostItemResponse> contentList) {
        return new PostItemListResponse(contentList);
    }
}
