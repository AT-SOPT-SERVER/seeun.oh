package org.sopt.service;


import org.sopt.domain.Post;
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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


}
