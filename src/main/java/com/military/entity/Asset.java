package com.military.entity;
import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private EquipmentType type;
    
    @Enumerated(EnumType.STRING)
    private AssetStatus status = AssetStatus.AVAILABLE;
    
    public enum AssetStatus{
        AVAILABLE, ASSIGNED, EXPENDED
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_id")
    @JsonIgnore  
    private Base base;
    
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
