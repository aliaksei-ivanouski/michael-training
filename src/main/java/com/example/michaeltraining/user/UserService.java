package com.example.michaeltraining.user;

public interface UserService {
    UserDTO getUser(Long id);
    void saveUser(UserDTO dto);
    void deleteUser(Long id);
}
