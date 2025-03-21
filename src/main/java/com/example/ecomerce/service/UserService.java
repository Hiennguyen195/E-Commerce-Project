package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Get a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Create a new user
    public User createUser(UserCreationRequest request) {
        User user = new User();

        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setBirthDate(request.getBirthDate());

        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
