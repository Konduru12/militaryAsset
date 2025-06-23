package com.military.repository;

import com.military.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByDatePurchasedBetweenAndBaseIdAndAssetTypeId(
        LocalDateTime startDate, LocalDateTime endDate, Long baseId, Long typeId);
    
    @Query("SELECT COALESCE(SUM(p.quantity), 0) FROM Purchase p " +
    	       "WHERE p.base.id = :baseId " +
    	       "AND p.asset.type.id = :typeId " +
    	       "AND (:start IS NULL OR p.datePurchased >= :start) " +
    	       "AND (:end IS NULL OR p.datePurchased <= :end)")
    	int sumQuantityByBaseIdAndTypeIdAndDateRange(
    	    @Param("baseId") Long baseId,
    	    @Param("typeId") Long typeId,
    	    @Param("start") LocalDateTime start,
    	    @Param("end") LocalDateTime end);

}
