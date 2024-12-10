package com.example.jwtexample.controller;

import com.example.jwtexample.model.JwtResponse;
import com.example.jwtexample.model.LoginRequest;
import com.example.jwtexample.model.Register;
import com.example.jwtexample.service.UserService;
import com.example.jwtexample.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private TokenUtility tokenUtility;

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Register registerRequest) {
        try {
            String message = userService.registerUser(registerRequest);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 409 Conflict
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed due to an unexpected error.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<Register> login = userService.userLogin(loginRequest);
        if (login != null) {
            return ResponseEntity.ok(new JwtResponse(tokenUtility.createToken(login.get().getUser_id(), login.get().getRole())));
        } else {
            return new ResponseEntity<>("User login not successfully", HttpStatus.ACCEPTED);
        }
    }
}

