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
public class UserUpdateRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    String userName;

    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String role;

}
