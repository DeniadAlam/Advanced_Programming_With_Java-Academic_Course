package com.student.accommodation.service;

import com.student.accommodation.dto.RoomBookingDto;
import com.student.accommodation.model.Room;
import com.student.accommodation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingService {

    @Autowired
    private RoomRepository roomRepository;

    // Check if the room is available
    public boolean checkRoomAvailability(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber);

        if (room != null && room.isAvailability()) {
            return true;  // Room is available
        }

        return false;  // Room is not available
    }

    // Book a room
    public boolean bookRoom(RoomBookingDto roomBookingDto) {
        // Implement room booking logic (change availability status, assign student, etc.)
        Room room = roomRepository.findByRoomNumber(roomBookingDto.getRoomNumber());

        if (room != null && room.isAvailability()) {
            room.setAvailability(false);  // Mark room as booked
            roomRepository.save(room);  // Save the updated room status
            return true;
        }

        return false;  // Room not available
    }
}
