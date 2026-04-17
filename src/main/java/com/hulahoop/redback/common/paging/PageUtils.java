package com.hulahoop.redback.common.paging;

/*
 * PageUtils
 * 페이징 처리에 공통으로 필요한 계산 로직을 모아 둔 유틸리티 클래스
 */
public class PageUtils {

    // 현재 페이지 번호와 페이지 크기를 받아 SQL OFFSET 값을 계산해 반환
    // 예: page=2, size=10 이면 offset=10 → 11번째 행부터 조회
    public static int calcOffset(int page, int size) {
        return (page - 1) * size;
    }

}
