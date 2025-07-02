package com.student.accommodation.repository;

import com.student.accommodation.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all students
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";  // Make sure the table name is correct
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    // Find student by ID
    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), id);
        return students.isEmpty() ? null : students.get(0);
    }

    // Save student to the database
    public void save(Student student) {
        String sql = "INSERT INTO students (name, email, department) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getDepartment());
    }

    // Update student by ID
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, department = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getDepartment(), student.getId());
    }

    // Delete student by ID
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
