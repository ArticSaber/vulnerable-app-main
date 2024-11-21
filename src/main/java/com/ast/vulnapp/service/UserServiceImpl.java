package com.ast.vulnapp.service;

import com.ast.vulnapp.entity.User;
import com.ast.vulnapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository users;

    public UserServiceImpl(UserRepository users) {
        this.users = users;
    }

    public User create(String email, String password, String fullName) {
        String id = UUID.randomUUID().toString().substring(18);
        User user = new User(id, email, password, fullName);
        try {
            return new User(this.users.save(user));
        } catch (RuntimeException e) {
            logger.error("Failed to register user " + user, e);
            return null;
        }
    }

    public Optional<User> validateUser(String email, String password) {
        Optional<User> user = this.users.findByEmailAndPassword(email, password);

        return user;
    }
}

