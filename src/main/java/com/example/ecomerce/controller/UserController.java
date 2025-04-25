package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserDTO;
import com.example.ecomerce.dto.request.user.UserPageRequest;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.dto.response.APIResponse;
import com.example.ecomerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    User updateUser(@PathVariable Long userId, @RequestBody @Valid UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping
    Page<UserDTO> getAllUsers(@ModelAttribute UserPageRequest request) {
        return userService.getAllUsers(request);
    }

    @GetMapping("/{userName}")
    APIResponse<UserDTO> getUserByUserName(@PathVariable String userName) {
        APIResponse apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.findByUserName(userName));
        return apiResponse;
    }

    @GetMapping("/id/{userId}")
    APIResponse<UserDTO> getUserById(@PathVariable Long userId) {
        APIResponse apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.getUserById(userId));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    //Find user by role
    @GetMapping("/role")
    Page<UserDTO> getUsersByRole(@RequestParam String role, @ModelAttribute UserPageRequest request) {
        return userService.getUsersByRole(role, request);
    }

    //Find user by userName or email
    @GetMapping("/search")
    Page<UserDTO> findByUserNameOrEmail(@RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "email", required = false) String email,
                                        @RequestParam(name = "role", required = false) String role,
                                        @ModelAttribute UserPageRequest request) {
        System.out.println("username: " + username);
        System.out.println("email: " + email);
        return userService.getUsersByEmailOrUsername(username, email, role, request);
    }
}
