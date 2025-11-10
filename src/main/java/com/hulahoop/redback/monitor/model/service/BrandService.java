package com.hulahoop.redback.monitor.model.service;

import com.hulahoop.redback.config.ServerConfig;
import com.hulahoop.redback.monitor.model.dao.BrandMapper;
import com.hulahoop.redback.monitor.model.dto.BrandDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BrandService {

    private final BrandMapper brandMapper;
    private final ServerConfig serverConfig;

    public BrandService(BrandMapper brandMapper, ServerConfig serverConfig) {
        this.brandMapper = brandMapper;
        this.serverConfig = serverConfig;
    }

    // ✅ DB 브랜드 + config 서버 병합
    public List<Map<String, Object>> getAllBrandServers() {
        List<BrandDTO> brands = brandMapper.selectAllBrands();
        List<ServerConfig.ServerInfo> servers = serverConfig.getServers();
        List<Map<String, Object>> result = new ArrayList<>();

        for (BrandDTO brand : brands) {
            Optional<ServerConfig.ServerInfo> matched = servers.stream()
                    .filter(s -> s.getBrandCode().equals(brand.getBrandCode()))
                    .findFirst();

            Map<String, Object> map = new HashMap<>();
            map.put("brandCode", brand.getBrandCode());
            map.put("brandName", brand.getBrandName());
            map.put("categoryCode", brand.getCategoryCode());
            map.put("categoryName", brand.getCategoryName());
            map.put("description", brand.getDescription());

            if (matched.isPresent()) {
                ServerConfig.ServerInfo s = matched.get();
                map.put("baseUrl", s.getBaseUrl());
                map.put("port", s.getPort());
                map.put("healthPath", s.getHealthPath());
            } else {
                map.put("baseUrl", "N/A");
                map.put("port", null);
                map.put("healthPath", "N/A");
            }

            result.add(map);
        }

        return result;
    }
}
