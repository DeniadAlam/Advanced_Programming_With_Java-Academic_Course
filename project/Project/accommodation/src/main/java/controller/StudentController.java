package com.student.accommodation.controller;

import com.student.accommodation.model.Student;
import com.student.accommodation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID (Profile View)
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            return ResponseEntity.ok(student); // Return the student if found
        }
        return ResponseEntity.status(404).body(null); // Return 404 if student is not found
    }

    // Create new student
    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "Student created successfully.";
    }

    // Update student by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);  // Ensure the ID is set for updating
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent != null) {
            studentRepository.update(student); // Update the student if found
            return ResponseEntity.ok("Student updated successfully.");
        }
        return ResponseEntity.status(404).body("Student not found.");
    }

    // Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent != null) {
            studentRepository.delete(id); // Delete the student if found
            return ResponseEntity.ok("Student deleted successfully.");
        }
        return ResponseEntity.status(404).body("Student not found.");
    }
}
