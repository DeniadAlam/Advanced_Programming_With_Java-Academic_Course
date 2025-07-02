package com.student.accommodation.model;

public class Room {

    private int roomId; // The unique ID of the room
    private String roomNumber; // The number of the room (e.g., "A101", "B203")
    private String roomType; // Type of room (e.g., "Single", "Double", "Suite")
    private boolean availability; // Whether the room is available (true) or booked (false)
    private double pricePerNight; // Price for booking per night
    private int studentId; // The ID of the student who has booked the room (null or 0 if available)

    // New Fields for Room Swap Feature
    private boolean isSwapRequested;  // Whether a swap request has been made for this room
    private String swapReason;  // Reason for the swap request
    private int requestedByStudentId;  // The student ID who requested the swap
    private boolean isSwapApproved;  // Whether the swap has been approved

    // Getters and Setters for all fields

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Getter and Setter for Swap Feature
    public boolean isSwapRequested() {
        return isSwapRequested;
    }

    public void setSwapRequested(boolean swapRequested) {
        isSwapRequested = swapRequested;
    }

    public String getSwapReason() {
        return swapReason;
    }

    public void setSwapReason(String swapReason) {
        this.swapReason = swapReason;
    }

    public int getRequestedByStudentId() {
        return requestedByStudentId;
    }

    public void setRequestedByStudentId(int requestedByStudentId) {
        this.requestedByStudentId = requestedByStudentId;
    }

    public boolean isSwapApproved() {
        return isSwapApproved;
    }

    public void setSwapApproved(boolean swapApproved) {
        isSwapApproved = swapApproved;
    }
}
