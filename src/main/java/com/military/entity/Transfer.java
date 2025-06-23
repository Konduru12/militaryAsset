package com.military.entity;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "transfers")
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_base_id")
	private Base fromBase;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_base_id")
	private Base toBase;
	
	private LocalDateTime transferDate;
	private int quantity;

}
