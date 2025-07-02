package com.student.accommodation.controller;

import com.student.accommodation.service.RoomSwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room-swap")
public class RoomSwapController {

    @Autowired
    private RoomSwapService roomSwapService;

    // Request room swap
    @PostMapping("/request")
    public ResponseEntity<String> requestRoomSwap(@RequestParam int currentRoomId,
                                                  @RequestParam int newRoomId,
                                                  @RequestParam int studentId,
                                                  @RequestParam String reason) {
        boolean isSwapRequested = roomSwapService.requestRoomSwap(currentRoomId, newRoomId, studentId, reason);

        if (isSwapRequested) {
            return ResponseEntity.ok("Room swap request initiated.");
        } else {
            return ResponseEntity.status(400).body("Room swap failed. Either rooms are unavailable or invalid data.");
        }
    }

    // Approve or reject swap request
    @PutMapping("/approve/{roomId}")
    public ResponseEntity<String> approveRoomSwap(@PathVariable int roomId, @RequestParam boolean isApproved) {
        boolean isSwapProcessed = roomSwapService.approveOrRejectSwap(roomId, isApproved);

        if (isSwapProcessed) {
            return ResponseEntity.ok("Room swap request processed successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to process the swap request.");
        }
    }

    // Get all rooms with pending swap requests
    @GetMapping("/requests")
    public ResponseEntity<?> getRoomsWithSwapRequests() {
        return ResponseEntity.ok(roomSwapService.getRoomsWithSwapRequests());
    }
}
