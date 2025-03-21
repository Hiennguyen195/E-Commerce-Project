package com.example.ecomerce.dto.request.user;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "Username must be at least 3 characters long")
    String userName;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String role;


}
