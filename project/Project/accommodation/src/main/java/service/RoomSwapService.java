package com.student.accommodation.service;

import com.student.accommodation.model.Room;
import com.student.accommodation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSwapService {

    @Autowired
    private RoomRepository roomRepository;

    // Request room swap
    public boolean requestRoomSwap(int currentRoomId, int newRoomId, int studentId, String reason) {
        Room currentRoom = roomRepository.findById(currentRoomId);
        Room newRoom = roomRepository.findById(newRoomId);

        if (currentRoom == null || newRoom == null || !currentRoom.isAvailability() || !newRoom.isAvailability()) {
            return false; // Either room doesn't exist or is not available
        }

        // Mark the current room with a swap request
        currentRoom.setSwapRequested(true);
        currentRoom.setSwapReason(reason);
        currentRoom.setRequestedByStudentId(studentId);

        // Save the updated current room data
        roomRepository.save(currentRoom);
        return true; // Swap request initiated
    }

    // Approve or reject room swap
    public boolean approveOrRejectSwap(int roomId, boolean isApproved) {
        Room room = roomRepository.findById(roomId);

        if (room == null) {
            return false; // Room not found
        }

        if (!room.isSwapRequested()) {
            return false; // No swap requested for this room
        }

        // Set the swap approval status
        room.setSwapApproved(isApproved);
        roomRepository.updateRoomSwapStatus(roomId, isApproved);

        // If approved, update availability for both rooms
        if (isApproved) {
            room.setAvailability(false); // Set current room as not available
            roomRepository.save(room); // Save the room's new status
        }

        return true;
    }

    // Get all rooms with swap requests
    public List<Room> getRoomsWithSwapRequests() {
        return roomRepository.findRoomsWithSwapRequests();
    }
}
