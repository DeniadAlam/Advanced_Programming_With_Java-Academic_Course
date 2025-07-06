# Advanced Programming with Java

This repository contains my coursework for the **Advanced Programming with Java** course, including two assignments and a final project built using Spring technologies.

---

## 📦 Assignment 1 — Inventory Management System (Spring Legacy)

- Developed an inventory system using Spring Legacy (XML configuration).
- Managed products, categories, expiry dates, and discounts.
- Generated reports for product stock and category-wise pricing.

---

## 🚀 Assignment 2 — Inventory Management System (Spring Boot)

- Migrated Assignment 1 project to Spring Boot.
- Integrated relational database for persistence.
- Added Spring Security with admin/user roles.
- Implemented wishlists, shopping carts, order history, and sales reports.
- Added AOP-based logging saved in `app.log`.

---

## 🏠 Student Accommodation Management System (Final Project)

For my final project, I developed a **Student Accommodation Management System** using Spring Boot and related technologies.

### Description

This application helps students find and manage accommodations while allowing admins to manage listings and user activities.

### Technologies Used

- **Spring Boot**  
  - Built RESTful services with no XML configurations.
  - Maintained layered architecture (Controller, Service, Repository).

- **Spring Security (JWT)**  
  - Implemented token-based authentication (JWT).
  - Defined user roles (ADMIN and USER).
  - Configured CORS for secure API access.

- **Spring AOP**  
  - Created logs for accessing controllers, services, and repositories.
  - Logs include username, accessed methods, timestamps.
  - All logs stored in `application.log`.

- **Spring JDBC**  
  - Used Spring JDBC for all database interactions.

- **Version Control**  
  - Managed the project in a private GitHub repository.
  - Followed consistent commit practices (at least 25 commits).

### Core Features & APIs

- **Authentication & User Management:**
  - User Registration & Login
  - Password change and password recovery
  - Profile view and update
  - JWT token issuance and validation

- **Domain-Specific Features:**
  - Search available accommodations
  - Booking management
  - Payment records
  - Manage reviews and ratings
  - Admin CRUD operations for listings
  - Admin user management
  - View booking history
  - Email notifications using JavaMailSender

---

## 💻 Tech Stack

- Java
- Spring Boot
- Spring Security (JWT)
- Spring AOP
- Spring JDBC
- Maven / Gradle
- SQL Database

---

## Author

- Md. Deniad Alam
