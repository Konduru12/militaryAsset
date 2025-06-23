package com.military.entity;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "equipment_types")
public class EquipmentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}
