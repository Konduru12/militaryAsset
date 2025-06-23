package com.military.controller;

import com.military.entity.Purchase;
import com.military.entity.Asset;
import com.military.entity.Base;
import com.military.repository.AssetRepository;
import com.military.repository.BaseRepository;
import com.military.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    
    @Autowired 
    private PurchaseService purchaseService;
    
    @Autowired 
    private AssetRepository assetRepository;
    
    @Autowired 
    private BaseRepository baseRepository;
    
    @PostMapping
    public Purchase createPurchase(@RequestBody Map<String, Object> request) {
      
        Long assetId = ((Number) request.get("assetId")).longValue();
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        
        Long baseId = ((Number) request.get("baseId")).longValue();
        Base base = baseRepository.findById(baseId)
            .orElseThrow(() -> new RuntimeException("Base not found with id: " + baseId));
        
        int quantity = (int) request.get("quantity");
        String dateStr = (String) request.get("datePurchased");
        LocalDateTime datePurchased = LocalDateTime.parse(dateStr);

        Purchase purchase = new Purchase();
        purchase.setAsset(asset);
        purchase.setBase(base);
        purchase.setQuantity(quantity);
        purchase.setDatePurchased(datePurchased);
        
        return purchaseService.createPurchase(purchase);
    }
    
    @GetMapping
    public List<Purchase> getPurchases(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam(required = false) Long baseId,
        @RequestParam(required = false) Long typeId) {
        
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        
        if (startDate != null) {
            startDateTime = startDate.atStartOfDay();
        }
        if (endDate != null) {
            endDateTime = endDate.atTime(LocalTime.MAX); 
        }
        
        return purchaseService.getPurchases(startDateTime, endDateTime, baseId, typeId);
    }
}
