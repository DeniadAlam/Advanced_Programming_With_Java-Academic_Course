package com.student.accommodation.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")  // Allow this controller's endpoints to be accessed from this origin
@RestController
public class CorsController {

    @GetMapping("/example")
    public String example() {
        return "This is an example endpoint!";
    }
}
