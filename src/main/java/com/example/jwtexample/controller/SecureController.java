package com.example.jwtexample.controller;


import com.example.jwtexample.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;


@RestController
@RequestMapping("/api")
public class SecureController {


    @Autowired
    TokenUtility tokenUtility;

    @GetMapping
    public String message(){
        return "Hello";
    }


    @GetMapping("/user")
    public ResponseEntity<String> userAccess(@RequestAttribute("role") String role) {
        if ("USER".equals(role)) {
            return ResponseEntity.ok("User Content.");
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }


    @GetMapping("/admin")
    public ResponseEntity<?> adminAccess(@RequestAttribute("role") String role) {

        if ("ADMIN".equals(role)) {
            return ResponseEntity.ok("Admin Content ");
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }
}

