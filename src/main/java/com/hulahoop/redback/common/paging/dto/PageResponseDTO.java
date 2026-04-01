package com.hulahoop.redback.common.paging.dto;

import java.util.List;

/**
 * PageResponseDTO<T>
 * 프론트엔드로 페이징 처리된 목록 결과와 메타데이터(전체 건수, 페잊 수 등)를 반환하는 공통 응답 객체
 * - 제네릭 <T>를 통해 DTO 타입에 상관없이 범용적으로 사용 가능
 */
public class PageResponseDTO<T> {

    private List<T> content; // 실제 목록 데이터
    private int page; // 현재 페이지 번호
    private int size; // 페이지당 개수
    private long totalElements; // 전체 데이터 건수
    private int totalPages; // 계산된 전체 페이지 수

    // 생성자에서 totalElements와 size를 활용해 totalPages 자동 계산
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
