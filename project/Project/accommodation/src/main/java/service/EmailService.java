package com.student.accommodation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;  // JavaMailSender is provided by Spring Boot to send emails

    // Method to send a simple email
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("javahello385@gmail.com");  // Sender email
        message.setTo(to);  // Receiver email
        message.setSubject(subject);  // Email subject
        message.setText(text);  // Email content
        mailSender.send(message);  // Send the email
    }

    // Method to send registration email
    public void sendRegistrationEmail(String to) {
        String subject = "Welcome to Student Accommodation!";
        String text = "Hello,\n\nThank you for registering with us. Your account has been created successfully.";
        sendEmail(to, subject, text);  // Call sendEmail() to send the registration email
    }

    // Method to send password reset email
    public void sendPasswordResetEmail(String to, String resetLink) {
        String subject = "Password Reset Request";
        String text = "Hello,\n\nWe received a request to reset your password. Please click the following link to reset your password:\n" + resetLink;
        sendEmail(to, subject, text);  // Call sendEmail() to send the reset email
    }
}
