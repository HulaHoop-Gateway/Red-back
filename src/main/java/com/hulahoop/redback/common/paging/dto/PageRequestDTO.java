package com.hulahoop.redback.common.paging.dto;

/*
 * PageRequestDTO
 * 클라이언트에서 목록 데이터를 요청할 때 사용하는 공통 파라미터 객체
 * 페이징(page, size), 정렬(sort), 날짜 범위, 필터(가맹점, 카테고리, 브랜드, 키워드) 조건을 담는다
 */
public class PageRequestDTO {

    private int page;            // 현재 페이지 번호 (기본값 1)
    private int size;            // 페이지당 행 수 (기본값 10)

    private String startDate;    // 조회 시작일
    private String endDate;      // 조회 종료일

    private String paymentDate;  // 단일 결제일 필터 (날짜 하나로 딱 잘라 조회할 때)

    private String merchantCode; // 가맹점 코드 필터
    private String categoryCode; // 카테고리 코드 필터
    private String brandCode;    // 브랜드 코드 필터

    private String sort;         // 정렬 기준 컬럼명
    private String groupBy;      // 통계 그룹 단위 (day, month 등)

    private String keyword;      // 이름, 코드 등 자유 검색어

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    // 페이지 번호가 0 이하로 내려오면 강제로 1페이지로 고정
    public int getPage() {
        return page <= 0 ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    // 페이지 크기가 0 이하로 오면 기본값 10으로 처리
    public int getSize() {
        return size <= 0 ? 10 : size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // MyBatis 쿼리의 OFFSET #{offset}에 그대로 매핑되는 값을 계산해 반환
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
