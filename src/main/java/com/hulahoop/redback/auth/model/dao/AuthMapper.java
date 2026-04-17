package com.hulahoop.redback.auth.model.dao;

import com.hulahoop.redback.auth.model.dto.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

// 관리자 계정 단건 조회 전용 MyBatis 매퍼 인터페이스
@Mapper
public interface AuthMapper {

    // id 기준으로 user_type='A'인 활성 관리자 계정 한 건을 조회
    AuthDTO findAdminById(String id);
}
