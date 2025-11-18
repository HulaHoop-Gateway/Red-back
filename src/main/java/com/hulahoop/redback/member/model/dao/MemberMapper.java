package com.hulahoop.redback.member.model.dao;

import java.util.List;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    // 기존 기능 유지
    List<MemberDTO> selectAllMembers();

    // ⭐ phone_number 로 member_code 조회
    String findMemberCodeByPhone(String phoneNumber);
}
