package com.military.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.military.entity.EquipmentType;


public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
}
