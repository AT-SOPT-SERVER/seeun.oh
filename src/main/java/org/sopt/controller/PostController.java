package org.sopt.controller;

import org.sopt.global.common.ApiResponse;
import org.sopt.global.common.SuccessCode;
import org.sopt.dto.req.PostCreateRequest;
import org.sopt.dto.req.PostUpdateRequest;
import org.sopt.dto.res.*;
import org.sopt.service.PostService;
import org.sopt.validation.PostValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/contents")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(
            @RequestBody PostCreateRequest postCreateRequest
    ) {
        String inputTitle = postCreateRequest.title();
        PostValidator.validateTitleLength(inputTitle);

        PostCreateResponse postCreateResponse = postService.createPost(postCreateRequest.title());

        return ResponseEntity.status(SuccessCode.CONTENT_CREATED.getStatus())
                .body(ApiResponse.success(SuccessCode.CONTENT_CREATED, postCreateResponse));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<PostItemListResponse>> getAllPosts() {
        PostItemListResponse listResponse = postService.getAllPosts();
        return ResponseEntity.status(SuccessCode.GET_ALL_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.GET_ALL_CONTENT, listResponse));
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(
            @PathVariable Long contentId
    ) {
        PostDetailResponse detailResponse = postService.getPost(contentId);
        return ResponseEntity.status(SuccessCode.GET_DETAIL_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.GET_DETAIL_CONTENT, detailResponse));

    }

    @PatchMapping("/{contentId}")
    public ResponseEntity<?> updatePost(
            @PathVariable (name="contentId") Long contentId,
            @RequestBody PostUpdateRequest postUpdateRequest
    ) {
        String updateTitle = postUpdateRequest.title();
        PostValidator.validateTitleLength(updateTitle);

        PostUpdateResponse updateItem = postService.updatePost(contentId, postUpdateRequest.title());
        return ResponseEntity.status(SuccessCode.UPDATE_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.UPDATE_CONTENT, updateItem));

    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long contentId
    ) {
        postService.deletePost(contentId);
        return ResponseEntity.status(SuccessCode.DELETE_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.DELETE_CONTENT));
    }

    @GetMapping(value = "", params = "keyword")
    public ResponseEntity<ApiResponse<PostSearchListResponse>> getPostsByKeyword(
            @RequestParam("keyword") String keyword
    ) {
        PostSearchListResponse searchList = postService.getListByKeyword(keyword);

        return ResponseEntity.status(SuccessCode.SEARCH_KEYWORD.getStatus())
                .body(ApiResponse.success(SuccessCode.SEARCH_KEYWORD, searchList));

    }
}
