package com.student.accommodation.service;

import com.student.accommodation.model.AccommodationHistory;
import com.student.accommodation.repository.AccommodationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationHistoryService {

    @Autowired
    private AccommodationHistoryRepository accommodationHistoryRepository;

    // Add a new accommodation history record
    public void addAccommodationHistory(AccommodationHistory accommodationHistory) {
        accommodationHistoryRepository.save(accommodationHistory);
    }

    // Get accommodation history for a student
    public List<AccommodationHistory> getAccommodationHistory(int studentId) {
        return accommodationHistoryRepository.findHistoryByStudentId(studentId);
    }
}
