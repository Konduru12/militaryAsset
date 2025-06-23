package com.military.controller;

import com.military.entity.Assignment;
import com.military.entity.Asset;
import com.military.entity.Personnel;
import com.military.repository.AssetRepository;
import com.military.repository.PersonnelRepository;
import com.military.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private PersonnelRepository personnelRepository;

    @PostMapping
    public Assignment createAssignment(@RequestBody Map<String, Object> request) {
        
        Long assetId = ((Number) request.get("assetId")).longValue();
        Asset asset = assetRepository.findByIdWithDetails(assetId)
            .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        
        Long personnelId = ((Number) request.get("personnelId")).longValue();
        Personnel personnel = personnelRepository.findByIdWithBase(personnelId)
            .orElseThrow(() -> new RuntimeException("Personnel not found with id: " + personnelId));
        
        String dateStr = (String) request.get("assignedDate");
        LocalDate assignedDate = LocalDate.parse(dateStr);

        Assignment assignment = new Assignment();
        assignment.setAsset(asset);
        assignment.setPersonnel(personnel);
        assignment.setAssignedDate(assignedDate);

        asset.setStatus(Asset.AssetStatus.ASSIGNED);
        assetRepository.save(asset);

        return assignmentService.save(assignment);
    }
    
    @PutMapping("/{id}/return")
    public Assignment returnAssignment(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        
        String dateStr = (String) request.get("returnedDate");
        LocalDate returnedDate = LocalDate.parse(dateStr);
        assignment.setReturnedDate(returnedDate);
        
        Asset asset = assignment.getAsset();
        asset.setStatus(Asset.AssetStatus.AVAILABLE);
        assetRepository.save(asset);
        
        return assignmentService.save(assignment);
    }

    @GetMapping
    public List<Assignment> getAssignments(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam(required = false) Long assetId,
        @RequestParam(required = false) Long personnelId) {
        
        return assignmentService.getAssignments(startDate, endDate, assetId, personnelId);
    }
}
