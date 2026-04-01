package com.hulahoop.redback.dashboard.model.dto;

import java.util.List;
import java.util.Map;

/**
 * DashboardDTO
 * 대시보드 화면에 필요한 모든 요약 통계를 하나의 객체로 취합한 DTO
 * - 프론트에서 단건 API 호출로 대시보드 모든 데이터를 받기 위한 데이터 컴테이너
 */
public class DashboardDTO {
    private long totalMembers; // 전체 가입 회원 수
    private long totalMerchants; // 전체 등록 가맹점 수
    private long totalApiRequests; // 전체 API 호출 회수 합계
    private long totalTransactions; // 요효 완결 결제 건수 (status='S', 취소 제외)

    private List<Map<String, Object>> dailyTransactions; // 최근 7일치 일별 결제 금액 데이터
    private List<Map<String, Object>> monthlyTransactions; // 월별 결제 금액 데이터 (그래프용)
    private List<Map<String, Object>> categoryRatio; // 카테고리별 거래 비율 (Movie vs Bike)
    private List<Map<String, Object>> movieBikeCategoryRatio; // 베를도 함께 표현하는 카테고리 비율

    public DashboardDTO() {
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(long totalMembers) {
        this.totalMembers = totalMembers;
    }

    public long getTotalMerchants() {
        return totalMerchants;
    }

    public void setTotalMerchants(long totalMerchants) {
        this.totalMerchants = totalMerchants;
    }

    public long getTotalApiRequests() {
        return totalApiRequests;
    }

    public void setTotalApiRequests(long totalApiRequests) {
        this.totalApiRequests = totalApiRequests;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public List<Map<String, Object>> getDailyTransactions() {
        return dailyTransactions;
    }

    public void setDailyTransactions(List<Map<String, Object>> dailyTransactions) {
        this.dailyTransactions = dailyTransactions;
    }

    public List<Map<String, Object>> getMonthlyTransactions() {
        return monthlyTransactions;
    }

    public void setMonthlyTransactions(List<Map<String, Object>> monthlyTransactions) {
        this.monthlyTransactions = monthlyTransactions;
    }

    public List<Map<String, Object>> getCategoryRatio() {
        return categoryRatio;
    }

    public void setCategoryRatio(List<Map<String, Object>> categoryRatio) {
        this.categoryRatio = categoryRatio;
    }

    public List<Map<String, Object>> getMovieBikeCategoryRatio() {
        return movieBikeCategoryRatio;
    }

    public void setMovieBikeCategoryRatio(List<Map<String, Object>> movieBikeCategoryRatio) {
        this.movieBikeCategoryRatio = movieBikeCategoryRatio;
    }
}
