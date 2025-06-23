package com.military.controller;

import com.military.entity.EquipmentType;
import com.military.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment-types")
public class EquipmentTypeController {

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @PostMapping
    public EquipmentType createEquipmentType(@RequestBody EquipmentType equipmentType) {
        return equipmentTypeService.createEquipmentType(equipmentType);
    }
}
