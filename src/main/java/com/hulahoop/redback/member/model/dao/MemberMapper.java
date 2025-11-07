package com.hulahoop.redback.member.model.dao;

import java.util.List;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    List<MemberDTO> selectAllMembers();
}
