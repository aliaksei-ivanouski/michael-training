package com.example.michaeltraining.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void getUser(@RequestBody UserDTO dto) {
        userService.saveUser(dto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id")Long id){
        userService.deleteUser(id);
    }
}
