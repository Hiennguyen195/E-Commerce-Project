package com.example.ecomerce.dto.request.user;

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
    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long cartId;
    private String role;
}
