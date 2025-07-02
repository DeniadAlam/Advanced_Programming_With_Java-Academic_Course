package com.student.accommodation.repository;

import com.student.accommodation.model.RoomReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a review
    public void save(RoomReview roomReview) {
        String sql = "INSERT INTO room_reviews (room_id, username, rating, review_text) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, roomReview.getRoomId(), roomReview.getUsername(), roomReview.getRating(), roomReview.getReviewText());
    }

    // Get all reviews for a specific room
    public List<RoomReview> findByRoomId(int roomId) {
        String sql = "SELECT * FROM room_reviews WHERE room_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RoomReview.class), roomId);
    }

    // Calculate the average rating for a specific room
    public double calculateAverageRating(int roomId) {
        String sql = "SELECT AVG(rating) FROM room_reviews WHERE room_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{roomId}, Double.class);
    }
}
