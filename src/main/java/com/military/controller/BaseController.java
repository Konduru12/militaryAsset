package com.military.controller;
import com.military.entity.Base;
import com.military.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bases")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @PostMapping
    public Base createBase(@RequestBody Base base) {
        return baseService.createBase(base);
    }
}

