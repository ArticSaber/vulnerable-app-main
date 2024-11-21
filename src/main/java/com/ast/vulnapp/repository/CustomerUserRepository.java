package com.ast.vulnapp.repository;

import com.ast.vulnapp.entity.User;

import java.util.Optional;

public interface CustomerUserRepository {
    Optional<User> findByEmailAndPassword(String email, String password);
}
