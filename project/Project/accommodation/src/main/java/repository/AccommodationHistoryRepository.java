package com.student.accommodation.repository;

import com.student.accommodation.model.AccommodationHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccommodationHistoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all accommodation history for a student
    public List<AccommodationHistory> findHistoryByStudentId(int studentId) {
        String sql = "SELECT * FROM accommodation_history WHERE student_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AccommodationHistory.class), studentId);
    }

    // Add a new accommodation history record
    public void save(AccommodationHistory accommodationHistory) {
        String sql = "INSERT INTO accommodation_history (student_id, room_id, check_in_date, check_out_date, price_per_night, room_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, accommodationHistory.getStudentId(), accommodationHistory.getRoomId(),
                accommodationHistory.getCheckInDate(), accommodationHistory.getCheckOutDate(),
                accommodationHistory.getPricePerNight(), accommodationHistory.getRoomType());
    }
}
