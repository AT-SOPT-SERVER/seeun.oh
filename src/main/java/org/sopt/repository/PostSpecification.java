package org.sopt.repository;

import org.sopt.constant.Tag;
import org.sopt.domain.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<Post> titleContains(String title) {
        return (root, query, criteriaBuilder) ->
                (title.equals("all")) ? null : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Post> nicknameContains(String nickname) {
        return (root, query, criteriaBuilder) ->
                (nickname.equals("all")) ? null : criteriaBuilder.like(root.get("user").get("nickname"), "%" + nickname + "%");
    }

    public static Specification<Post> hasTag(Tag tag) {
        return (root, query, criteriaBuilder) ->
                (tag == null) ? null : criteriaBuilder.equal(root.get("tag"), tag);
    }
}
