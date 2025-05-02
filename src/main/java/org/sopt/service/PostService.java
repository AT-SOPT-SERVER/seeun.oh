package org.sopt.service;

import org.sopt.constant.Tag;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.post.req.PostCreateRequest;
import org.sopt.dto.post.req.PostUpdateRequest;
import org.sopt.dto.post.res.*;
import org.sopt.global.exception.InvalidRequestException;
import org.sopt.global.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;
import org.sopt.repository.PostSpecification;
import org.sopt.validation.PostValidator;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.sopt.global.exception.PostErrorCode.*;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostCreateResponse createPost(
            PostCreateRequest postCreateRequest,
            final User user
    ) {
        Optional<Post> lastPost = postRepository.findTopByUserOrderByCreatedAtDesc(user); //getLatestPostByUser() - 에러 발생

        // 입력 시간 제한(3분) 검증
        lastPost.ifPresent(post ->
            PostValidator.validateCoolTime(post.getCreatedAt())
        );

        // 제목 중복 검증
        checkDuplicateTitle(postCreateRequest.title());

        //태그 타입 변환
        Tag tag = Tag.fromValue(postCreateRequest.tag());

        Post postEntity = Post.of(postCreateRequest.title(), postCreateRequest.content(), user, tag);
        Post savedPost = postRepository.save(postEntity);
        return PostCreateResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public PostItemListResponse getAllPosts() {
        List<Post> posts = postRepository.getAllPostsOrderByLatest();

        List<PostItemResponse> postList =  posts.stream()
                .map(post -> PostItemResponse.of(post.getId(), post.getTitle(), post.getUser().getNickname()))
                .toList();

        return PostItemListResponse.of(postList);

    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(final Long contentId) {
        Post post = postRepository.findById(contentId)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_ID));

        return PostDetailResponse.of(post.getId(), post.getTitle(), post.getContent(), post.getUser().getNickname(), post.getTag().getValue());
    }

    @Transactional
    public PostUpdateResponse updatePost(
            final Long contentId,
            final PostUpdateRequest postUpdateRequest,
            final User user
    ) {
        Post post = postRepository.findByIdAndUser(contentId, user)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_USER_AND_POST));

        // 제목 중복 검증
        checkDuplicateTitle(postUpdateRequest.title());

        post.updatePost(postUpdateRequest.title(), postUpdateRequest.content());

        return PostUpdateResponse.of(post.getId(), post.getTitle(), post.getContent());
    }

    @Transactional
    public void deletePost(
            final Long contentId,
            final User user
    ) {
        Post post = postRepository.findByIdAndUser(contentId, user)
                .orElseThrow(() -> new PostNotFoundException(NOT_FOUND_USER_AND_POST));

        postRepository.deleteById(post.getId());
    }

    @Transactional(readOnly = true)
    public PostSearchListResponse getListByKeywords(
            final String title,
            final String nickname,
            final String inputTag
    ) {

        //태그 타입 변환
        Tag tag = null;
        if (!inputTag.equals("all")) {
            tag = Tag.fromValue(inputTag);
        }


        Specification<Post> spec = Specification
                .where(PostSpecification.titleContains(title))
                .and(PostSpecification.nicknameContains(nickname))
                .and(PostSpecification.hasTag(tag));

        List<Post> searchedPosts = postRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<PostSearchResponse> searchList = searchedPosts.stream()
                .map(post -> PostSearchResponse.of(post.getId(), post.getTitle(), post.getTag().getValue()))
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
