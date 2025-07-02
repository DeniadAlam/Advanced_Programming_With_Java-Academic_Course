package com.student.accommodation.controller;

import com.student.accommodation.model.AccommodationHistory;
import com.student.accommodation.service.AccommodationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation-history")
public class AccommodationHistoryController {

    @Autowired
    private AccommodationHistoryService accommodationHistoryService;

    // Get accommodation history by student ID
    @GetMapping("/{studentId}")
    public ResponseEntity<List<AccommodationHistory>> getAccommodationHistory(@PathVariable int studentId) {
        List<AccommodationHistory> history = accommodationHistoryService.getAccommodationHistory(studentId);
        if (history.isEmpty()) {
            return ResponseEntity.status(404).body(null); // No history found
        }
        return ResponseEntity.ok(history); // Return found history
    }

    // Add a new accommodation history record
    @PostMapping("/add")
    public ResponseEntity<String> addAccommodationHistory(@RequestBody AccommodationHistory accommodationHistory) {
        accommodationHistoryService.addAccommodationHistory(accommodationHistory);
        return ResponseEntity.ok("Accommodation history added successfully.");
    }
}
