package com.example.michaeltraining.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    Page<User.UserProjection> getUsers(Pageable pageable);
    User.UserProjection getUser(UUID uuid);
    void saveUser(User.NewUserProjection projection);
}
