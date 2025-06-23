package com.military.entity;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	private LocalDateTime datePurchased;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "base_id")
	private Base base;

}
