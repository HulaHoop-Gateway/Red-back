package com.hulahoop.redback.merchant.model.service;

import com.hulahoop.redback.merchant.model.dao.MerchantMapper;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    public List<MerchantDTO> getAllMerchants() {
        return merchantMapper.selectAllMerchants();
    }
}
