package com.military.service;

import com.military.entity.Base;
import com.military.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private BaseRepository baseRepository;

    public Base createBase(Base base) {
        return baseRepository.save(base);
    }
}
