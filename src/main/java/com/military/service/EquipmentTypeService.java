package com.military.service;

import com.military.entity.EquipmentType;
import com.military.repository.EquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentTypeService {

    @Autowired
    private EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentType createEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeRepository.save(equipmentType);
    }
}
