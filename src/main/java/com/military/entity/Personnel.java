package com.military.entity;
import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "personnel")
public class Personnel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "base_id")
	private Base base;

}
