/*
package controller;

public class TestController {
}
*/

package com.student.accommodation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")  // Add this annotation to set the base path
public class TestController {

    @PostMapping("/json")  // Map the /json POST request
    public String testJson(@RequestBody Map<String, String> payload) {
        return "Received JSON: " + payload.toString();  // Return the received JSON as string
    }
}
