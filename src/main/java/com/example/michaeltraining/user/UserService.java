package com.example.michaeltraining.user;

public interface UserService {
    UserDTO getUser(Long id);
    void saveUser(UserDTO dto);
    void updateUser(Long id, User user);

   // void updateUser(Long id, UserDTO dto);
}
