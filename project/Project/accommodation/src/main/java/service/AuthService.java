package com.student.accommodation.service;

import com.student.accommodation.dto.AuthDto;
import com.student.accommodation.model.User;
import com.student.accommodation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.student.accommodation.util.JwtTokenUtil;
import com.student.accommodation.dto.ProfileUpdateDto;
import com.student.accommodation.dto.ChangePasswordDto;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;  // For sending emails

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register a new user
    public void register(AuthDto authDto) {
        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(authDto.getPassword());

        // Create user object
        User user = new User();
        user.setUsername(authDto.getUsername());
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_USER");  // default user role

        // Save user to DB
        userRepository.save(user);
    }

    // Authenticate user
    public String authenticate(AuthDto authDto) {
        User user = userRepository.findByUsername(authDto.getUsername());

        // Log the status of the user lookup
        if (user == null) {
            System.out.println("User not found: " + authDto.getUsername());
        } else if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            System.out.println("Password mismatch for user: " + authDto.getUsername());
        }

        if (user != null && passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            // Log the token before returning
            String token = JwtTokenUtil.generateToken(user.getUsername());
            System.out.println("Generated JWT token: " + token); // Log the generated token
            return token;
        }

        return null; // Return null if authentication fails
    }

    // Update Profile Method
    public boolean updateProfile(String username, ProfileUpdateDto profileUpdateDto) {
        // Find the user by username
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // Update user details with the data from the DTO
            user.setName(profileUpdateDto.getName());
            user.setEmail(profileUpdateDto.getEmail());
            user.setDepartment(profileUpdateDto.getDepartment());

            // Save the updated user information in the database
            userRepository.save(user);
            System.out.println("User updated successfully");
            return true;  // Return true if update is successful
        }

        System.out.println("User not found in the database");
        return false;  // Return false if the user is not found
    }




    // Method to change the password
    public boolean changePassword(String username, ChangePasswordDto changePasswordDto) {
        // Fetch the user from the database
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            // Check if the new password and confirm password match
            if (changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
                // Encode the new password
                String encodedNewPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());
                user.setPassword(encodedNewPassword);
                userRepository.save(user); // Save the updated user to the database
                return true; // Password change successful
            } else {
                return false; // New password and confirm password do not match
            }
        }

        return false; // Old password is incorrect or user not found
    }


    // Method to handle password reset request
    public boolean resetPasswordRequest(String email) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // Generate a password reset token (using UUID for simplicity)
            String resetToken = generateResetToken();

            // Send the password reset link via email
            sendResetPasswordEmail(email, resetToken);
            return true; // Password reset request successfully processed
        }

        return false; // Return false if the user with the email does not exist
    }

    // Helper method to generate a reset token (UUID-based token for simplicity)
    private String generateResetToken() {
        return java.util.UUID.randomUUID().toString();
    }

    // Helper method to send the password reset email
    private void sendResetPasswordEmail(String email, String resetToken) {
        // Create a simple email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the following link: \n" +
                "http://localhost:8080/api/auth/resetPassword?token=" + resetToken);

        // Send the email
        mailSender.send(message);



    }


    public void sendRegistrationEmail(String recipientEmail) {
        // Create the message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Registration Successful");
        message.setText("Welcome! You have successfully registered for the Accommodation Service.");

        // Send the email
        mailSender.send(message);
    }
}
