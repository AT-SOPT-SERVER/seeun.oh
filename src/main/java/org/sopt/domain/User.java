package org.sopt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();
    protected User() {}

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public static User of(String nickname) {
        return new User(nickname);
    }
}
