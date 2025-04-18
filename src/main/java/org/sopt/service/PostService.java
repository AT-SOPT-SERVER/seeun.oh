package org.sopt.service;


import org.sopt.common.ErrorCode;
import org.sopt.domain.Post;
import org.sopt.dto.*;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostResponse createPost(
            final String title
    ) {
        Post postEntity = Post.of(title);
        Post savedPost = postRepository.save(postEntity);
        return PostResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public PostListResponse getAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostItemResponse> postList =  posts.stream()
                .map(post -> PostItemResponse.of(post.getId(), post.getTitle()))
                .toList();

        return PostListResponse.of(postList);

    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(final Long contentId) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_FOUND_ID.getMessage()));

        return PostDetailResponse.of(post.getId(), post.getTitle());
    }

    @Transactional
    public PostUpdateResponse updatePost(
            final Long contentId,
            final String title
    ) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.updateTitle(title);

        return PostUpdateResponse.of(post.getId(), post.getTitle());
    }

    @Transactional
    public void deletePost(final Long contentId) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_FOUND_ID.getMessage()));

        postRepository.deleteById(post.getId());
    }

}
