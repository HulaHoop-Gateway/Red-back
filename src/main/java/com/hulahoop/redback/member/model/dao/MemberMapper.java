package com.hulahoop.redback.member.model.dao;

import java.util.List;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

// 회원 데이터 조회 전용 MyBatis 매퍼 인터페이스
@Mapper
public interface MemberMapper {

    // 전체 회원 전체 조회 (limit 없음, 하위 호환 전용)
    List<MemberDTO> selectAllMembers();

    // 페이징 + 키워드 검색 조회 (PageRequestDTO 의 offset/size/keyword 활용)
    List<MemberDTO> selectMembersPaged(PageRequestDTO requestDTO);

    // 페이징네이션을 위한 전체 회원 긴수 집계 (동일 검색 조건 적용)
    long countMembers(PageRequestDTO requestDTO);

    // 전화번호(폰니본)으로 member_code를 단건 조회 (Transaction 역사 읽기 시 활용)
    String findMemberCodeByPhone(String phoneNumber);
}
