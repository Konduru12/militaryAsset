package com.military.repository;

import com.military.entity.Transfer;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Transfer t " +
           "WHERE t.toBase.id = :baseId " +
           "AND t.asset.type.id = :typeId " +
           "AND (:start IS NULL OR t.transferDate >= :start) " +
           "AND (:end IS NULL OR t.transferDate <= :end)")
    int sumTransfersIn(
        @Param("baseId") Long baseId,
        @Param("typeId") Long typeId,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Transfer t " +
           "WHERE t.fromBase.id = :baseId " +
           "AND t.asset.type.id = :typeId " +
           "AND (:start IS NULL OR t.transferDate >= :start) " +
           "AND (:end IS NULL OR t.transferDate <= :end)")
    int sumTransfersOut(
        @Param("baseId") Long baseId,
        @Param("typeId") Long typeId,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end);

    List<Transfer> findByTransferDateBetweenAndFromBaseIdAndToBaseId(
            LocalDateTime startDate, LocalDateTime endDate, Long fromBaseId, Long toBaseId);
}
