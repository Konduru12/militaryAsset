package com.military.entity;
import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)private UserRole role;
	
	@ManyToOne
	@JoinColumn(name = "base_id")
	private Base base;
	
	public enum UserRole{
		ADMIN, BASE_COMMANDER, LOGISTICS_OFFICER
	}

}
