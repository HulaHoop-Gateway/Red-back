package com.hulahoop.redback.merchant.controller;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import com.hulahoop.redback.merchant.model.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public List<MerchantDTO> getAllMerchants() {
        return merchantService.getAllMerchants();
    }
}
