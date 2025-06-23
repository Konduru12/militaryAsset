package com.military.repository;

import com.military.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    @Query("SELECT p FROM Personnel p LEFT JOIN FETCH p.base WHERE p.id = :id")
    Optional<Personnel> findByIdWithBase(@Param("id") Long id);
}
