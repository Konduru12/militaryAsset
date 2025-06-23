package com.military.repository;
import com.military.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByAssignedDateBetweenAndAssetIdAndPersonnelId(
        LocalDate startDate, LocalDate endDate, Long assetId, Long personnelId);
}
