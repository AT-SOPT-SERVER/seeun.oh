package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.constant.Tag;

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

    @Enumerated(EnumType.STRING)
    private Tag tag;

    public Post() {}

    public Post(String title, String content, User user, Tag tag) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public User getUser() {
        return this.user;
    }

    public Tag getTag() {
        return this.tag;
    }

    public void updatePost(final String newTitle, final String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

    public static Post of(String title, String content, User user, Tag tag) {
        return new Post(title, content, user, tag);
    }
}
