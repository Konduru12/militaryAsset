package com.military.controller;

import com.military.dto.DashboardMetrics;
import com.military.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    
    @Autowired 
    private DashboardService dashboardService;
    
    @GetMapping("/metrics")
    public DashboardMetrics getMetrics(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam(required = false) Long baseId,
        @RequestParam(required = false) Long typeId) {
        
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        
        if (startDate != null) {
            startDateTime = startDate.atStartOfDay();
        }
        
        if (endDate != null) {
            endDateTime = endDate.atTime(LocalTime.MAX);
        }
        
        return dashboardService.getMetrics(startDateTime, endDateTime, baseId, typeId);
    }
}
