package com.student.accommodation.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role; // can be "ROLE_ADMIN" or "ROLE_USER"
    private String name; // New field for user's name
    private String email; // New field for user's email
    private String department; // New field for user's department

    // Getters and Setters for id, username, password, role
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getters and Setters for name, email, and department (added fields)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
