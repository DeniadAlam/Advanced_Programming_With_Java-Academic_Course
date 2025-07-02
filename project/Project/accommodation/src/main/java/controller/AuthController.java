package com.student.accommodation.controller;

import com.student.accommodation.dto.ProfileUpdateDto;
import com.student.accommodation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.student.accommodation.dto.AuthDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import com.student.accommodation.dto.PasswordResetDto;
import org.springframework.http.HttpStatus;
import java.util.Base64;
import com.student.accommodation.dto.ChangePasswordDto;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDto authDto) {
        authService.register(authDto);

        // Trigger email notification after successful registration
        authService.sendRegistrationEmail(authDto.getEmail());

        return ResponseEntity.ok("User registered successfully.");
    }

    // Login user
    @PostMapping("/login")
    public String login(@RequestBody AuthDto authDto) {
        String token = authService.authenticate(authDto);

        if (token != null) {
            return "Login successful! Token: " + token;  // Return the token
        }

        return "Invalid username or password!";
    }

    // Profile Update
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateDto profileUpdateDto, @RequestHeader("Authorization") String token) {
        String username = extractUsernameFromBasicAuth(token);  // Use the helper method for Basic Auth

        boolean updated = authService.updateProfile(username, profileUpdateDto);

        if (updated) {
            return ResponseEntity.ok().body("Profile updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Password Reset Request
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        boolean isResetRequested = authService.resetPasswordRequest(passwordResetDto.getEmail());

        if (isResetRequested) {
            return ResponseEntity.ok("Password reset link has been sent to your email.");
        }
        return ResponseEntity.status(400).body("Error: Unable to send password reset link.");
    }

    // Change password
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto,
                                                 @RequestHeader("Authorization") String token) {
        // Extract the username from the token
        String username = extractUsernameFromBasicAuth(token);

        // Call the service to change the password
        boolean isPasswordChanged = authService.changePassword(username, changePasswordDto);

        if (isPasswordChanged) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to change password. Either the old password is incorrect or the new passwords don't match.");
        }
    }

    // Helper method to extract username from Basic Auth token
    private String extractUsernameFromBasicAuth(String token) {
        String base64Credentials = token.substring(6);  // Remove "Basic " prefix
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        final String[] values = credentials.split(":", 2);
        return values[0];  // Return the username
    }


}
