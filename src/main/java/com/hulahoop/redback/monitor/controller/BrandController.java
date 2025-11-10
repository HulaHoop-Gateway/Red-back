package com.hulahoop.redback.monitor.controller;

import com.hulahoop.redback.monitor.model.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/servers")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // ✅ DB + config 병합된 브랜드 서버 정보 조회
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getServerList() {
        return ResponseEntity.ok(brandService.getAllBrandServers());
    }
}
