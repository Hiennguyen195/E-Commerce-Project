package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserDTO;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable Long userId ,@RequestBody @Valid UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
