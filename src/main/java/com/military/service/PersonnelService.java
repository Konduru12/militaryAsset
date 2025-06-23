package com.military.service;

import com.military.entity.Personnel;
import com.military.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public Personnel createPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }
}
