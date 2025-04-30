package org.sopt.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Post() {}

    public Post(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void updateTitle(final String newTitle) {
        this.title = newTitle;
    }

    public static Post of(String title) {
        return new Post(title);
    }
}
