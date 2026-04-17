package com.hulahoop.redback.member.model.dao;

import java.util.List;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

// 회원 데이터 조회 전용 MyBatis 매퍼 인터페이스
@Mapper
public interface MemberMapper {

    // 전체 회원 조회, limit 없음 (이전 스펙 호환용)
    List<MemberDTO> selectAllMembers();

    // 페이징 + 키워드 검색 적용 회원 목록 조회 (offset, size, keyword 활용)
    List<MemberDTO> selectMembersPaged(PageRequestDTO requestDTO);

    // 페이징 처리를 위한 전체 회원 수 집계 (검색 조건 동일 적용)
    long countMembers(PageRequestDTO requestDTO);

    // 전화번호로 member_code 단건 조회 (결제 내역 삽입 시 활용)
    String findMemberCodeByPhone(String phoneNumber);
}
