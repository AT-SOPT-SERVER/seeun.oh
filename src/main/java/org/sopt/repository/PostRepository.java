package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> searchByKeyword(String keyword);
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Optional<Post> getLatestPost();
    boolean existsByTitle(String title);
}
