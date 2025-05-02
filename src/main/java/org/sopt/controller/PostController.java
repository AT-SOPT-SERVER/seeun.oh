package org.sopt.controller;

import org.sopt.domain.User;
import org.sopt.dto.post.res.*;
import org.sopt.global.common.ApiResponse;
import org.sopt.global.common.SuccessCode;
import org.sopt.dto.post.req.PostCreateRequest;
import org.sopt.dto.post.req.PostUpdateRequest;
import org.sopt.service.PostService;
import org.sopt.service.UserService;
import org.sopt.validation.PostValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/contents")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(
            @RequestHeader Long userId,
            @RequestBody PostCreateRequest postCreateRequest
    ) {
        User user = userService.getUser(userId);

        PostValidator.validateTitleLength(postCreateRequest.title());
        PostValidator.validateContentLength(postCreateRequest.content());

        PostCreateResponse postCreateResponse = postService.createPost(postCreateRequest, user);

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
            @RequestHeader Long userId,
            @PathVariable (name="contentId") Long contentId,
            @RequestBody PostUpdateRequest postUpdateRequest
    ) {
        User user = userService.getUser(userId);

        PostValidator.validateTitleLength(postUpdateRequest.title());
        PostValidator.validateContentLength(postUpdateRequest.content());

        PostUpdateResponse updateItem = postService.updatePost(contentId, postUpdateRequest, user);
        return ResponseEntity.status(SuccessCode.UPDATE_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.UPDATE_CONTENT, updateItem));

    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<?> deletePost(
            @RequestHeader Long userId,
            @PathVariable Long contentId
    ) {
        User user = userService.getUser(userId);

        postService.deletePost(contentId, user);
        return ResponseEntity.status(SuccessCode.DELETE_CONTENT.getStatus())
                .body(ApiResponse.success(SuccessCode.DELETE_CONTENT));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PostSearchListResponse>> getPostsByKeywords(
            @RequestParam(required = false, defaultValue = "all") String title,
            @RequestParam(required = false, defaultValue = "all") String nickname,
            @RequestParam(required = false, defaultValue = "all") String tag
    ) {
        PostSearchListResponse searchList = postService.getListByKeywords(title, nickname, tag);

        return ResponseEntity.status(SuccessCode.SEARCH_KEYWORD.getStatus())
                .body(ApiResponse.success(SuccessCode.SEARCH_KEYWORD, searchList));

    }
}
