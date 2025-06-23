package com.military.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.military.entity.Base;


public interface BaseRepository extends JpaRepository<Base, Long> {
}
