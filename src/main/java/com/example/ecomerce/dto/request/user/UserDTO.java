package com.example.ecomerce.dto.request.user;

import com.example.ecomerce.entity.Cart;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;
//    @Size(min = 8, message = "PASSWORD_INVALID")
//    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long cartId;
    private String role;

    public UserDTO( Long id, String userName, String email, String firstName, String lastName, LocalDate birthDate, Cart cart, String role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cartId = cart.getId();
        this.role = role;
    }
}
