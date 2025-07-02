package com.student.accommodation.controller;

import com.student.accommodation.model.RoomReview;
import com.student.accommodation.service.RoomReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class RoomReviewController {

    @Autowired
    private RoomReviewService roomReviewService;

    // Endpoint to add a review for a room
    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody RoomReview roomReview) {
        roomReviewService.addReview(roomReview);
        return ResponseEntity.ok("Review added successfully.");
    }

    // Endpoint to get all reviews for a specific room
    @GetMapping("/room/{roomId}")
    public List<RoomReview> getReviewsForRoom(@PathVariable int roomId) {
        return roomReviewService.getReviewsForRoom(roomId);
    }

    // Endpoint to get the average rating for a specific room
    @GetMapping("/room/{roomId}/averageRating")
    public ResponseEntity<Double> getAverageRatingForRoom(@PathVariable int roomId) {
        double averageRating = roomReviewService.getAverageRatingForRoom(roomId);
        return ResponseEntity.ok(averageRating);
    }
}
