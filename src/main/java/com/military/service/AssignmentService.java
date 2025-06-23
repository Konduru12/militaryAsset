package com.military.service;

import com.military.entity.Assignment;
import com.military.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class AssignmentService {
    
    @Autowired 
    private AssignmentRepository assignmentRepository;
    
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
    
    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }
    
    public List<Assignment> getAssignments(LocalDate startDate, LocalDate endDate, Long assetId, Long personnelId) {
        return assignmentRepository.findByAssignedDateBetweenAndAssetIdAndPersonnelId(
            startDate, endDate, assetId, personnelId);
    }
}
