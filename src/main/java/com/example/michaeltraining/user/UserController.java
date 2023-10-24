package com.example.michaeltraining.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User operations")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(description = "Get pageable list of users")
    public Page<User.UserProjection> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("/{uuid}")
    @Operation(description = "Get user by uuid")
    public User.UserProjection getUser(@PathVariable("uuid") UUID uuid) {
        return userService.getUser(uuid);
    }

    @PostMapping
    @Operation(description = "Save new user")
    public void saveUser(@RequestBody User.NewUserProjection projection) {
        userService.saveUser(projection);
    }

    @DeleteMapping("/{uuid}")
    @Operation(description = "Delete user by uuid")
    public void deleteUser(@PathVariable("uuid") UUID uuid) {
        userService.deleteUser(uuid);
    }
}
