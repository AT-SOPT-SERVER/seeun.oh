package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.res.*;
import org.sopt.exception.InvalidRequestException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;
import org.sopt.validation.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.sopt.exception.ErrorCode.DUPLICATE_TITLE;
import static org.sopt.exception.ErrorCode.NOT_FOUND_ID;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostCreateResponse createPost(
            final String title
    ) {
        Optional<Post> lastPost = postRepository.findTopByOrderByCreatedAtDesc();

        // 입력 시간 제한(3분) 검증
        lastPost.ifPresent(post ->
            PostValidator.validateCoolTime(post.getCreatedAt())
        );

        // 제목 중복 검증
        checkDuplicateTitle(title);

        Post postEntity = Post.of(title);
        Post savedPost = postRepository.save(postEntity);
        return PostCreateResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public PostItemListResponse getAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostItemResponse> postList =  posts.stream()
                .map(post -> PostItemResponse.of(post.getId(), post.getTitle()))
                .toList();

        return PostItemListResponse.of(postList);

    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(final Long contentId) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_ID));

        return PostDetailResponse.of(post.getId(), post.getTitle());
    }

    @Transactional
    public PostUpdateResponse updatePost(
            final Long contentId,
            final String title
    ) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_ID));

        // 제목 중복 검증
        checkDuplicateTitle(title);

        post.updateTitle(title);

        return PostUpdateResponse.of(post.getId(), post.getTitle());
    }

    @Transactional
    public void deletePost(final Long contentId) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_ID));

        postRepository.deleteById(post.getId());
    }

    @Transactional(readOnly = true)
    public PostSearchListResponse getListByKeyword(final String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        List<PostSearchResponse> searchList = posts.stream()
                .map(post -> PostSearchResponse.of(post.getId(), post.getTitle()))
                .toList();

        return PostSearchListResponse.of(searchList);
    }

    private void checkDuplicateTitle(final String title) {
        // 제목 중복 검증
        boolean isAlreadyExists = postRepository.existsByTitle(title);
        if (isAlreadyExists) {
            throw new InvalidRequestException(DUPLICATE_TITLE);
        }
    }

}
