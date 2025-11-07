package com.hulahoop.redback.member.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hulahoop.redback.member.model.dao.MemberMapper;
import com.hulahoop.redback.member.model.dto.MemberDTO;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<MemberDTO> getAllMembers() {
        return memberMapper.selectAllMembers();
    }
}
