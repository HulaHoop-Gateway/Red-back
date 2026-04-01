package com.hulahoop.redback.monitor.controller;

// BrandController (= 서버 모니터)
// 판매 브랜드(로엀 등 마이크로서비스)의 서버 목록과 헬스체크 URL 데이터를 제공하는 API
// DB의 T_Brand 데이터와 application.yml에 설정된 URL 정보를 함치염 리턴
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.monitor.model.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servers")
public class BrandController {

    private final BrandService brandService;

    // 생성자 주입
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // GET /api/servers?page=&size=&keyword=
    // 페이징 + 키워드 필터를 지원하는 서버(브랜드) 목록 조회 API
    @GetMapping
    public ResponseEntity<PageResponseDTO<?>> getServerList(PageRequestDTO requestDTO) {
        return ResponseEntity.ok(brandService.getMergedServersPaged(requestDTO));
    }
}
