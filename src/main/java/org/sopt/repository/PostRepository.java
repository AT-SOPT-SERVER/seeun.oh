package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findOne(int id) {

        for(Post post : postList) {
            if(post.getId() == id) {
                return post;
            }
        }
        //존재하지 않는 id인 경우
        return null;
    }

    public Boolean update(int id, String title) {
        Post post = findOne(id);
        if(post != null) {
            post.setTitle(title);
            return true;
        } else {
            return false;
        }
    }

    public Boolean delete(int id) {
        Post post = findOne(id);
        if(post != null) {
            postList.remove(post);
            return true;
        } else {
            return false;
        }
    }

    public List<Post> findByKeyword(String keyword) {
            List<Post> searchPosts = new ArrayList<>();
            if(keyword == null || keyword.trim().isEmpty()) {
                return searchPosts;
            }
            for(Post post : postList) {
                if(post.getTitle().contains(keyword)) {
                    searchPosts.add(post);
                }
            }
            return searchPosts;
    }

    public Boolean existsByTitle(String title) {
        for(Post post : postList) {
            if(post.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
