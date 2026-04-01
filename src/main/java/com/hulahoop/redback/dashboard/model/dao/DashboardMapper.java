package com.hulahoop.redback.dashboard.model.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

// 대시보드 집계 쿼리를 담당하는 MyBatis 매퍼 인터페이스
@Mapper
public interface DashboardMapper {

    // T_Member 테이블 전체 로우 수 체크 (유효 또는 탈퇴 회원 로우 포함)
    long countTotalMembers();

    // T_Merchant 테이블 전체 가맹점 등록 수 집계
    long countTotalMerchants();

    // T_API 테이블에서 call_count 합산 (COALESCE 널 방어 포함)
    long countTotalApiRequests();

    // 요효 결제(S) 건수 집계, 취소된 건 포함 안 시킴로 NOT EXISTS 필터 적용
    long countTotalTransactions();

    // 최근 7일간 일별 결제액 합계 (빈 날짜는 상위 서비스에서 0으로 잡대하는 로직 병용)
    List<Map<String, Object>> selectDailyTransactions();

    // 2025-06 ~ 현재까지 월별 결제액 합계 (status='S', 취소 제외)
    List<Map<String, Object>> selectMonthlyTransactions();

    // 카테고리별 거래 건수 비율 (T_Category JOIN 활용)
    List<Map<String, Object>> selectCategoryRatio();
}
