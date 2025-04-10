package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.util.IdGenerator;

import java.util.List;

import static org.sopt.exception.CommonException.DUPLICATE_TITLE;

public class PostService {

    private final PostRepository postRepository = new PostRepository();

    public void createPost(String title) {
        int postId = IdGenerator.getId();
        Post post = new Post(postId, title);
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findOne(id);
    }

    public Boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public Boolean updatePost(int id, String title) {
        return postRepository.update(id, title);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByKeyword(keyword);
    }

    public void validateDuplicateTitle(String title) {
        if(postRepository.existsByTitle(title)) {
            throw new IllegalArgumentException(DUPLICATE_TITLE.getMessage());
        }
    }
}
