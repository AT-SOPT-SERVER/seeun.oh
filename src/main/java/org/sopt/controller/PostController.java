package org.sopt.controller;

import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessCode;
import org.sopt.dto.*;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/contents")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestBody PostRequest postRequest
    ) {
        PostResponse postResponse = postService.createPost(postRequest.title());

        return ResponseEntity.status(SuccessCode.CONTENT_CREATED.getStatus())
                .body(ApiResponse.success(SuccessCode.CONTENT_CREATED, postResponse));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts() {
        PostListResponse listResponse = postService.getAllPosts();
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
