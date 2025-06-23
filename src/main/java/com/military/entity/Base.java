package com.military.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "bases")
public class Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String location;

}




