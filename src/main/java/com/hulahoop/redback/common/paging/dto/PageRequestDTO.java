package com.hulahoop.redback.common.paging.dto;

public class PageRequestDTO {

    private int page;     // 현재 페이지 번호
    private int size;     // 페이지당 데이터 개수

    private String startDate;    // 검색 시작일
    private String endDate;      // 검색 종료일

    private String merchantCode; // 가맹점 필터
    private String categoryCode; // 카테고리 필터
    private String brandCode;    // 🔥 브랜드 필터 (추가됨)

    private String sort;         // 정렬 옵션 (amount_desc 등)

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    /* ======== Getter / Setter ======== */
    public int getPage() {
        return page <= 0 ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size <= 0 ? 10 : size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return (getPage() - 1) * getSize();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
