package com.military.controller;

import com.military.entity.Transfer;
import com.military.entity.Asset;
import com.military.entity.Base;
import com.military.repository.AssetRepository;
import com.military.repository.BaseRepository;
import com.military.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private BaseRepository baseRepository;

    @PostMapping
    public Transfer createTransfer(@RequestBody Map<String, Object> request) {
       
        Long assetId = ((Number) request.get("assetId")).longValue();
        Asset asset = assetRepository.findByIdWithDetails(assetId)
            .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        
        Long fromBaseId = ((Number) request.get("fromBaseId")).longValue();
        Base fromBase = baseRepository.findById(fromBaseId)
            .orElseThrow(() -> new RuntimeException("From base not found with id: " + fromBaseId));
        
        Long toBaseId = ((Number) request.get("toBaseId")).longValue();
        Base toBase = baseRepository.findById(toBaseId)
            .orElseThrow(() -> new RuntimeException("To base not found with id: " + toBaseId));
        
        int quantity = (int) request.get("quantity");
        String dateStr = (String) request.get("transferDate");
        LocalDateTime transferDate = LocalDateTime.parse(dateStr);

        Transfer transfer = new Transfer();
        transfer.setAsset(asset);
        transfer.setFromBase(fromBase);
        transfer.setToBase(toBase);
        transfer.setQuantity(quantity);
        transfer.setTransferDate(transferDate);

        return transferService.createTransfer(transfer);
    }
}
