package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.authenticaltion.AuthenticationRequest;
import com.example.ecomerce.response.AuthenticationResponse;
import com.example.ecomerce.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return result;
    }


}
