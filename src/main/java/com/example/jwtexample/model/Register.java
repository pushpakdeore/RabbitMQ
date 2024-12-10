package com.example.jwtexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    @Id
    @GeneratedValue
    private Long user_id;

    private String username;
    private String password;
    private String role;
}

