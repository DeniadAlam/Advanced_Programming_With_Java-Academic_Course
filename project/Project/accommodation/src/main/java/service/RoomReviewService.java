package com.student.accommodation.service;

import com.student.accommodation.model.RoomReview;
import com.student.accommodation.repository.RoomReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;  // Add this import

@Service
public class RoomReviewService {

    @Autowired
    private RoomReviewRepository roomReviewRepository;

    // Add a review for a room
    public void addReview(RoomReview roomReview) {
        roomReviewRepository.save(roomReview);
    }

    // Get reviews for a room
    public List<RoomReview> getReviewsForRoom(int roomId) {
        return roomReviewRepository.findByRoomId(roomId);
    }

    // Calculate average rating for a room
    public double getAverageRatingForRoom(int roomId) {
        return roomReviewRepository.calculateAverageRating(roomId);
    }
}
