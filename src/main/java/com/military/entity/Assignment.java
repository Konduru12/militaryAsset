package com.military.entity;
import lombok.*;

import java.time.LocalDate;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "assignments")
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	private Personnel personnel;
	private LocalDate assignedDate;
	private LocalDate returnedDate;
	
}
