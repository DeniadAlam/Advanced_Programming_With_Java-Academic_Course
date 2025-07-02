package com.student.accommodation.controller;

import com.student.accommodation.dto.RoomBookingDto;
import com.student.accommodation.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roomBooking")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    // Book a room
    @PostMapping("/book")
    public ResponseEntity<String> bookRoom(@RequestBody RoomBookingDto roomBookingDto) {
        boolean isBooked = roomBookingService.bookRoom(roomBookingDto);

        if (isBooked) {
            return ResponseEntity.ok("Room booked successfully.");
        } else {
            return ResponseEntity.status(400).body("Room not available or invalid room.");
        }
    }

    // Check room availability
    @GetMapping("/checkAvailability/{roomNumber}")
    public ResponseEntity<String> checkRoomAvailability(@PathVariable String roomNumber) {
        boolean isAvailable = roomBookingService.checkRoomAvailability(roomNumber);

        if (isAvailable) {
            return ResponseEntity.ok("Room " + roomNumber + " is available.");
        } else {
            return ResponseEntity.status(400).body("Room " + roomNumber + " is not available.");
        }
    }
}
