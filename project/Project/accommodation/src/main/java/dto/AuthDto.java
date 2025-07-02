package com.student.accommodation.dto;

public class AuthDto {

    private String username;
    private String password;
    private String email;  // Add email field here

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;  // Make sure this method exists
    }

    public void setEmail(String email) {
        this.email = email;  // Make sure this method exists
    }
}
