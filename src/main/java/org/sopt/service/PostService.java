package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title) {
        Post post = new Post(title);
        postRepository.save(post);
        System.out.println("post title: " + post.getTitle());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


}
