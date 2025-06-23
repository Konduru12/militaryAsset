package com.military.repository;

import com.military.entity.Asset;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset,Long> {
    @Query("SELECT COUNT(a) FROM Asset a WHERE a.base.id = :baseId AND a.type.id = :typeId")
    int calculateOpeningBalance(@Param("baseId") Long baseId, 
                               @Param("typeId") Long typeId);
    
    @Query("SELECT COUNT(a) FROM Asset a WHERE a.base.id = :baseId AND a.type.id = :typeId AND a.status = 'ASSIGNED'")
    int countAssignedAssets(@Param("baseId") Long baseId, 
                           @Param("typeId") Long typeId);
    
    @Query("SELECT COUNT(a) FROM Asset a WHERE a.base.id = :baseId AND a.type.id = :typeId AND a.status = 'EXPENDED'")
    int countExpendedAssets(@Param("baseId") Long baseId, 
                           @Param("typeId") Long typeId);
    
    List<Asset> findByBaseIdAndTypeId(Long baseId, Long typeId);

    @Query("SELECT COUNT(a) FROM Asset a WHERE a.base.id = :baseId AND a.type.id = :typeId AND a.createdAt < :date")
    int calculateOpeningBalanceForDate(@Param("date") LocalDate date, 
                                      @Param("baseId") Long baseId, 
                                      @Param("typeId") Long typeId);
    
    @Query("SELECT a FROM Asset a LEFT JOIN FETCH a.type LEFT JOIN FETCH a.base WHERE a.id = :id")
    Optional<Asset> findByIdWithDetails(@Param("id") Long id);
    
    @Query("SELECT COUNT(a) FROM Asset a " +
    	       "WHERE a.base.id = :baseId " +
    	       "AND a.type.id = :typeId " +
    	       "AND a.createdAt < :dateTime")
    	int calculateOpeningBalanceForDateTime(@Param("dateTime") LocalDateTime dateTime,
    	                                      @Param("baseId") Long baseId,
    	                                      @Param("typeId") Long typeId);


}
