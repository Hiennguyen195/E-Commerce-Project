package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserDTO;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.response.APIResponse;
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
    APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {

        APIResponse<User> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse ;
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable Long userId ,@RequestBody @Valid UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping
    APIResponse<List<UserDTO>> getAllUsers() {
        APIResponse apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.getAllUsers());
        return apiResponse;
    }

    @GetMapping("/{userId}")
    APIResponse<UserDTO> getUserById(@PathVariable Long userId) {
        APIResponse apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.getUserById(userId));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
