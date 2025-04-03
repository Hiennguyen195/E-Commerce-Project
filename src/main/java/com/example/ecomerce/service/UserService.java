package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserDTO;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.enums.ErrorCode;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.exception.AppException;
import com.example.ecomerce.repository.CartRepository;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    // Get a user by id
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCartId(user.getCart().getId());

        return userDTO;
    }

    // Create a new user
    public User createUser(UserCreationRequest request) {
        User user = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }

        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user = userRepository.save(user);

        //Create a new cart for the first time created user
        Cart newCart = new Cart();
        newCart.setUser(user);
        cartRepository.save(newCart);

        user.setCart(newCart);

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
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserName(user.getUserName());
                    userDTO.setPassword(user.getPassword());
                    userDTO.setFirstName(user.getFirstName());
                    userDTO.setLastName(user.getLastName());
                    userDTO.setBirthDate(user.getBirthDate());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setRole(user.getRole());
                    userDTO.setCartId(user.getCart().getId());
                    return userDTO;
                })
                .toList();
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
