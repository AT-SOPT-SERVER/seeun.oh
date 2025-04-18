package org.sopt.service;


import org.sopt.domain.Post;
import org.sopt.dto.PostItemResponse;
import org.sopt.dto.PostListResponse;
import org.sopt.dto.PostResponse;
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


}
