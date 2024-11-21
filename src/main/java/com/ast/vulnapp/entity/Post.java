package com.ast.vulnapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    @Column(length=2048)
    private String text;

    Post() {}

    public Post(Long PostId, String text) {
        this.postId = PostId;
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }
}

