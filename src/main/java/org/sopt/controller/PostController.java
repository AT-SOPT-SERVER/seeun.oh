package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.time.LocalDateTime;
import java.util.List;


import static org.sopt.validation.PostValidator.validateCoolTime;
import static org.sopt.validation.PostValidator.validateTitleLength;

public class PostController {

    private final PostService postService = new PostService();
    private LocalDateTime lastPostTime;


    public void createPost(String title) {
        try {
            // 입력 유효 시간 검사
            validateCoolTime(lastPostTime);

            // 글자수 제한 검사
            validateTitleLength(title);

            // 제목 중복 입력 검사
            postService.validateDuplicateTitle(title);

            postService.createPost(title);
            lastPostTime = LocalDateTime.now();// 저장
            System.out.println("✅ 게시글이 성공적으로 저장되었습니다!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        if (postList.isEmpty()) {
            System.out.println("📭 등록된 게시글이 없습니다.");
        }
        return postList;
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean updatePostTitle(int id, String title) {
        boolean result = false;
        try {
            validateTitleLength(title);
            postService.validateDuplicateTitle(title);

            result = postService.updatePost(id, title);
            if(!result) {
                System.out.println("❌ 해당 ID의 게시글이 존재하지 않습니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

}
