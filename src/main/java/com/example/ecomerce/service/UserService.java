package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.user.UserCreationRequest;
import com.example.ecomerce.dto.request.user.UserDTO;
import com.example.ecomerce.dto.request.user.UserPageRequest;
import com.example.ecomerce.dto.request.user.UserUpdateRequest;
import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.Notification;
import com.example.ecomerce.entity.enums.ErrorCode;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.exception.AppException;
import com.example.ecomerce.mapper.UserMapper;
import com.example.ecomerce.repository.CartRepository;
import com.example.ecomerce.repository.NotificationRepository;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    // Get a user by id
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
//        userDTO.setPassword(user.getPassword());
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

        Notification noti = new Notification();
        noti.setMessage(" | Người dùng: " + user.getUserName() +" đã được thêm mới.");
        notificationRepository.save(noti);

        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(request.getUserName());
        user.setRole(request.getRole());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setBirthDate(request.getBirthDate());

        Notification noti = new Notification();
        noti.setMessage(" | Người dùng có ID " + userId + ": "+ user.getUserName() +" đã được cập nhật.");
        notificationRepository.save(noti);

        return userRepository.save(user);
    }

    // Get all users
    public Page<UserDTO> getAllUsers(UserPageRequest request) {
        return userRepository.findAll(request.toPageable())
                .map(UserMapper::toDTO); //Convert to DTO
    }

    // Delete a user
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(id);
        Notification noti = new Notification();
        noti.setMessage(" | Người dùng có ID " + id + ": "+ user.getUserName() +" đã bị xóa.");
        notificationRepository.save(noti);
    }

    // Find a user by username
    public UserDTO findByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new AppException(ErrorCode.USERNAME_INVALID);
        }

        if (!userRepository.existsByUserName(userName)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        // Find the user by username
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Map the user entity to UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
//        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCartId(user.getCart().getId());
        return userDTO;
    }

    // Find users by role
    public Page<UserDTO> getUsersByRole(String role, UserPageRequest request) {
        return userRepository.findUsersByRole(role, request.toPageable())
                .map(UserMapper::toDTO);
    }

    // Find users by email or username
    public Page<UserDTO> getUsersByEmailOrUsername(String userName, String email, String role, UserPageRequest request) {
        if (userName == null && email == null) {
            throw new AppException(ErrorCode.USERNAME_INVALID);
        }

        return userRepository.findByUserNameOrEmailContainingIgnoreCase(userName, email, role, request.toPageable())
                .map(UserMapper::toDTO);

    }

}
