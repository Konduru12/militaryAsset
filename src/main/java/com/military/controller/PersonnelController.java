package com.military.controller;

import com.military.entity.Personnel;
import com.military.entity.Base;
import com.military.repository.BaseRepository;
import com.military.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private BaseRepository baseRepository;

    @PostMapping
    public Personnel createPersonnel(@RequestBody Map<String, Object> request) {
        
        String name = (String) request.get("name");
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        Object baseIdObj = request.get("baseId");
        if (baseIdObj == null) {
            throw new RuntimeException("baseId is required");
        }
        Long baseId;
        try {
            baseId = ((Number) baseIdObj).longValue();
        } catch (ClassCastException e) {
            throw new RuntimeException("baseId must be a number");
        }

        Base base = baseRepository.findById(baseId)
            .orElseThrow(() -> new RuntimeException("Base not found with id: " + baseId));

        Personnel personnel = new Personnel();
        personnel.setName(name);
        personnel.setBase(base);

        return personnelService.createPersonnel(personnel);
    }
}
