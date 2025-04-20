package org.sopt.dto.res;

import java.util.List;

public record PostSearchListResponse(
        List<PostSearchResponse> contentList
) {
    public static  PostSearchListResponse of(final List<PostSearchResponse> searchList) {
        return new PostSearchListResponse(searchList);
    }
}
