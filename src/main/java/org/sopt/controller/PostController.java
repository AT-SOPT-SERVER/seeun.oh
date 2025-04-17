package org.sopt.controller;

import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessCode;
import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestBody PostRequest postRequest
    ) {
        PostResponse postResponse = postService.createPost(postRequest.title());

        return ResponseEntity.status(SuccessCode.CONTENT_CREATED.getStatus())
                .body(ApiResponse.success(SuccessCode.CONTENT_CREATED, postResponse));
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
