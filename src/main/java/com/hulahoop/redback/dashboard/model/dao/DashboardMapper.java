package com.hulahoop.redback.dashboard.model.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

// 대시보드 집계 쿼리를 담당하는 MyBatis 매퍼 인터페이스
@Mapper
public interface DashboardMapper {

    // T_Member 전체 행 수를 센다 (탈퇴 회원 포함)
    long countTotalMembers();

    // T_Merchant 전체 가맹점 등록 수를 집계한다
    long countTotalMerchants();

    // T_API 테이블의 call_count 합산 (NULL 방어를 위해 COALESCE 적용)
    long countTotalApiRequests();

    // 완료 상태(S) 결제 건수만 집계하고, 취소 내역은 제외한다
    long countTotalTransactions();

    // 최근 7일간 일별 결제 금액 합계를 조회 (비는 날짜는 서비스에서 0으로 채운다)
    List<Map<String, Object>> selectDailyTransactions();

    // 특정 시점부터 현재까지 월별 결제 금액 합계를 조회 (status='S', 취소 제외)
    List<Map<String, Object>> selectMonthlyTransactions();

    // 카테고리별 거래 건수 비율 조회 (T_Category 조인 활용)
    List<Map<String, Object>> selectCategoryRatio();
}
