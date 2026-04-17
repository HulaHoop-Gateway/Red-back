package com.hulahoop.redback.monitor.model.dto;

/**
 * BrandDTO
 * 판매 브랜드(마이크로서비스 담당 서버들) 기본 정보를 전달하는 DTO
 * - T_Brand 테이블과 매핑
 */
public class BrandDTO {
    private String brandCode; // 브랜드 고유 식별코드 (NB01, BW01 등)
    private String brandName; // 브랜드 상호명 (노바시네마, 바이크웨이 등)
    private String categoryCode; // 카테고리 코드 (Movie, Bike 등)
    private String categoryName; // 카테고리 명칭
    private String description; // 브랜드 부가 설명

    public BrandDTO() {
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
