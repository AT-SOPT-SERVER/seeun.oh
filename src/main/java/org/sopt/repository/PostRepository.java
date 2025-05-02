package org.sopt.repository;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    boolean existsByTitle(String title);
    @Query("SELECT p FROM Post p WHERE p.user = :user ORDER BY p.createdAt DESC")
    Optional<Post> getLatestPostByUser(@Param("user") User user);
    @Query("SELECT p FROM Post p WHERE p.id = :id AND p.user = :user")
    Optional<Post> findByIdAndUser(@Param("id") Long id, @Param("user") User user);
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> getAllPostsOrderByLatest();
    Optional<Post> findTopByUserOrderByCreatedAtDesc(User user);

}
