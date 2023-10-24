package com.example.michaeltraining.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findAllByUuid(UUID uuid);
}
