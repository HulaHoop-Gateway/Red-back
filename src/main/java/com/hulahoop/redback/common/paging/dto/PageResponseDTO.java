package com.hulahoop.redback.common.paging.dto;

import java.util.List;

/*
 * PageResponseDTO<T>
 * 페이징 처리된 목록 결과와 메타 정보를 함께 프론트에 반환하는 공통 응답 객체
 * 제네릭 타입 T를 사용하므로 어떤 DTO 타입이든 재사용할 수 있다
 */
public class PageResponseDTO<T> {

    private List<T> content;      // 실제 목록 데이터
    private int page;             // 현재 페이지 번호
    private int size;             // 페이지당 데이터 개수
    private long totalElements;   // 전체 데이터 건수
    private int totalPages;       // 전체 페이지 수 (totalElements / size 올림 계산)

    // 생성자 안에서 totalPages를 자동으로 계산해 저장
    public PageResponseDTO(List<T> content, int page, int size, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
