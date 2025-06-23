package com.military.service;
import com.military.entity.Transfer;
import com.military.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {
    
    @Autowired private TransferRepository transferRepository;
    
    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
    
    public List<Transfer> getTransfers(LocalDateTime startDate, LocalDateTime endDate, Long fromBaseId, Long toBaseId) {
        return transferRepository.findByTransferDateBetweenAndFromBaseIdAndToBaseId(
            startDate, endDate, fromBaseId, toBaseId);
    }
}
