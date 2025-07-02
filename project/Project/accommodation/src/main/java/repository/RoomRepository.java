package com.student.accommodation.repository;

import com.student.accommodation.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Find room by room number
    public Room findByRoomNumber(String roomNumber) {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";
        List<Room> rooms = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), roomNumber);
        return rooms.isEmpty() ? null : rooms.get(0);
    }

    // Save or update room status
    public void save(Room room) {
        String sql = "UPDATE rooms SET availability = ?, room_type = ?, price_per_night = ?, " +
                "is_swap_requested = ?, swap_reason = ?, requested_by_student_id = ?, is_swap_approved = ? " +
                "WHERE room_id = ?";
        jdbcTemplate.update(sql, room.isAvailability(), room.getRoomType(), room.getPricePerNight(),
                room.isSwapRequested(), room.getSwapReason(), room.getRequestedByStudentId(),
                room.isSwapApproved(), room.getRoomId());
    }

    // Check room availability by room ID
    public boolean isRoomAvailable(int roomId) {
        String sql = "SELECT availability FROM rooms WHERE room_id = ?";
        String availability = jdbcTemplate.queryForObject(sql, String.class, roomId);
        return "true".equalsIgnoreCase(availability);  // Assuming availability is a boolean stored as a string
    }

    // Find rooms with pending swap requests
    public List<Room> findRoomsWithSwapRequests() {
        String sql = "SELECT * FROM rooms WHERE is_swap_requested = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }

    // Find room by ID
    public Room findById(int id) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        List<Room> rooms = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), id);
        return rooms.isEmpty() ? null : rooms.get(0);
    }

    // Update room swap status
    public void updateRoomSwapStatus(int roomId, boolean isSwapApproved) {
        String sql = "UPDATE rooms SET is_swap_approved = ?, is_swap_requested = false WHERE room_id = ?";
        jdbcTemplate.update(sql, isSwapApproved, roomId);
    }

    // Retrieve all rooms
    public List<Room> findAll() {
        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }
}
