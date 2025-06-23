package com.military.service;

import com.military.dto.DashboardMetrics;
import com.military.repository.AssetRepository;
import com.military.repository.PurchaseRepository;
import com.military.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DashboardService {
    
    @Autowired 
    private AssetRepository assetRepository;
    @Autowired 
    private TransferRepository transferRepository;
    @Autowired 
    private PurchaseRepository purchaseRepository;
    
    public DashboardMetrics getMetrics(LocalDateTime startDateTime, 
                                      LocalDateTime endDateTime, 
                                      Long baseId, 
                                      Long typeId) {
        DashboardMetrics metrics = new DashboardMetrics();
        
        if (startDateTime != null) {
            metrics.setOpeningBalance(
                assetRepository.calculateOpeningBalanceForDateTime(
                    startDateTime, baseId, typeId
                )
            );
        } else {
            metrics.setOpeningBalance(0);
        }
        
        metrics.setPurchases(
            purchaseRepository.sumQuantityByBaseIdAndTypeIdAndDateRange(
                baseId, typeId, startDateTime, endDateTime
            )
        );
        
        metrics.setTransfersIn(
            transferRepository.sumTransfersIn(baseId, typeId, startDateTime, endDateTime)
        );
        
        metrics.setTransfersOut(
            transferRepository.sumTransfersOut(baseId, typeId, startDateTime, endDateTime)
        );
        
        metrics.setNetMovement(
            metrics.getPurchases() + metrics.getTransfersIn() - metrics.getTransfersOut()
        );
        
        metrics.setClosingBalance(
            metrics.getOpeningBalance() + metrics.getNetMovement()
        );
        
        metrics.setAssigned(
            assetRepository.countAssignedAssets(baseId, typeId)
        );
        
        metrics.setExpended(
            assetRepository.countExpendedAssets(baseId, typeId)
        );
        
        return metrics;
    }
}
