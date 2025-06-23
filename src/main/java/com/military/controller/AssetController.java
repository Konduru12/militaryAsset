package com.military.controller;
import com.military.entity.Asset;
import com.military.entity.Base;
import com.military.entity.EquipmentType;
import com.military.repository.BaseRepository;
import com.military.repository.EquipmentTypeRepository;
import com.military.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;
    @Autowired
    private BaseRepository baseRepository;
    @Autowired
    private EquipmentTypeRepository equipmentTypeRepository;

    @PostMapping
    public Asset createAsset(@RequestBody Map<String, Object> request) {
        String name = (String) request.get("name");
        Long baseId = ((Number) request.get("baseId")).longValue();
        Long typeId = ((Number) request.get("typeId")).longValue();
        String status = (String) request.get("status");
        Base base = baseRepository.findById(baseId)
            .orElseThrow(() -> new RuntimeException("Base not found with id: " + baseId));
        EquipmentType type = equipmentTypeRepository.findById(typeId)
            .orElseThrow(() -> new RuntimeException("EquipmentType not found with id: " + typeId));
        Asset asset = new Asset();
        asset.setName(name);
        asset.setBase(base);
        asset.setType(type);
        if (status != null) {
            asset.setStatus(Asset.AssetStatus.valueOf(status));
        }

        return assetService.createAsset(asset);
    }
}
