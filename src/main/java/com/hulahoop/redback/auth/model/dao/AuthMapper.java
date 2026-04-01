package com.hulahoop.redback.auth.model.dao;

import com.hulahoop.redback.auth.model.dto.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

// 관리자 처은 조회 전용 MyBatis 매퍼 인터페이스
@Mapper
public interface AuthMapper {

    // id 조건 + user_type='A' + member_yn='Y' 세 가지 조건으로
    // 활성 관리자 로움 단건 조회
    AuthDTO findAdminById(String id);
}
