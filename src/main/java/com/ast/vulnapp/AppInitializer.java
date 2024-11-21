package com.ast.vulnapp;

import com.ast.vulnapp.entity.Post;
import com.ast.vulnapp.repository.PostRepository;
import com.ast.vulnapp.service.UserServiceImpl;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements SmartInitializingSingleton {
    private final UserServiceImpl users;
    private final PostRepository posts;

    public AppInitializer(UserServiceImpl users, PostRepository posts) {
        this.users = users;
        this.posts = posts;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.users.create("mike@email.com", "Cat12", "Mike");
        this.users.create("jane@email.com", "Welcome", "Jane");
        this.posts.save(new Post(1L, "This is my first post!"));
        this.posts.save(new Post(1L, "This is my second post!"));
        this.posts.save(new Post(1L, "This is my third post!"));
    }
}