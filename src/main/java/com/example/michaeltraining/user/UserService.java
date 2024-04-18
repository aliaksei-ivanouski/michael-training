package com.example.michaeltraining.user;

public interface UserService {
    UserDTO getUser(Long id);
    void saveUser(UserDTO dto);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
