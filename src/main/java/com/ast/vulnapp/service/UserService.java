package com.ast.vulnapp.service;

import com.ast.vulnapp.entity.User;

import java.util.Optional;

public interface UserService {

    User create(String email, String password, String fullName);

    Optional<User> validateUser(String email, String password);
}
