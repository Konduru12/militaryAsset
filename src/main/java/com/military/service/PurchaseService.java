package com.military.service;

import com.military.entity.Purchase;
import com.military.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {
    
    @Autowired 
    private PurchaseRepository purchaseRepository;
    
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
    
    public List<Purchase> getPurchases(LocalDateTime startDate, LocalDateTime endDate, Long baseId, Long typeId) {
        
        if (baseId == null || typeId == null) {
            throw new IllegalArgumentException("baseId and typeId are required");
        }
        return purchaseRepository.findByDatePurchasedBetweenAndBaseIdAndAssetTypeId(
            startDate, endDate, baseId, typeId
        );
    }
}
