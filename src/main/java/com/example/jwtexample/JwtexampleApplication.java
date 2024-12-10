package com.example.jwtexample;

import com.example.jwtexample.util.TokenUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JwtexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtexampleApplication.class, args);
		TokenUtility tokenUtility = new TokenUtility();

		// Create a token
		String token = tokenUtility.createToken(12345L, "ADMIN");

		// Print the token
		System.out.println("Generated Token: " + token);

		// Decode the token to get the role
		String role = tokenUtility.getRoleFromToken(token);
		System.out.println("Role from Token: " + role);

		// Decode the token to get the employee ID
		Long empId = tokenUtility.getEmpIdFromToken(token);
		System.out.println("Employee ID from Token: " + empId);

		// Get the expiration time from the token
		Date expirationDate = tokenUtility.getExpirationFromToken(token);
		System.out.println("Token Expiration Time: " + expirationDate);
	}

}
