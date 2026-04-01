package com.hulahoop.redback.merchant.model.dto;

/**
 * MerchantDTO
 *
 * 가맹점 운영 정보를 담는 데이터 전송 객체 (DTO)
 * - DB의 T_Merchant, T_Brand, T_Category 테이블 조인 결과와 매핑됨
 */
public class MerchantDTO {

    // 가맹점 코드 식별자
    private String merchantCode;

    // 가맹점 상호명
    private String merchantName;

    // 사업자 등록 번호
    private String businessId;

    // 카테고리 명칭
    private String categoryName;

    // 등록 일자
    private String registrationDate;

    // 계약 만료일자
    private String terminationDate;

    // 현재 계약 상태 유지 여부
    private String contractStatus;

    // 시스템 내 카테고리 분류 코드 (Movie, Bike 등)
    private String categoryCode;

    // 프론트엔드 필터링용 외래 조인 필드
    private String brandCode; // 예: NB01
    private String brandName; // 예: 노바시네마

    // ───────────────────────────────
    // Getter / Setter
    // ───────────────────────────────

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    @Override
    public String toString() {
        return "MerchantDTO{" +
                "merchantCode='" + merchantCode + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", businessId='" + businessId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", terminationDate='" + terminationDate + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
