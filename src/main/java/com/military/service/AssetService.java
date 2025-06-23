package com.military.service;
import com.military.entity.Asset;
import com.military.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetService {
    
    @Autowired private AssetRepository assetRepository;
    
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
    
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }
    
    public Asset updateAsset(Long id, Asset assetDetails) {
        Asset asset = assetRepository.findById(id).orElseThrow();
        asset.setName(assetDetails.getName());
        asset.setBase(assetDetails.getBase());
        asset.setType(assetDetails.getType());
        asset.setStatus(assetDetails.getStatus());
        return assetRepository.save(asset);
    }
    
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}

