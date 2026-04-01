package com.hulahoop.redback.member.model.service;

// MemberService
// 회원 데이터 조회 서비스 담당 계층
// 전체 조회 + 페이징 줈리 두 가지 메서드를 제공
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.member.model.dao.MemberMapper;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    // 하위 호환용 전체 회원 목록 조회 (limit 없이 전체 반환)
    public List<MemberDTO> getAllMembers() {
        return memberMapper.selectAllMembers();
    }

    // 페이징 + 키워드 검색 데이터 제공
    // 리포드 수, 페이지 번호, 페이지 크기를 PageResponseDTO로 취합해 리턴
    public PageResponseDTO<MemberDTO> getMembers(PageRequestDTO requestDTO) {

        List<MemberDTO> list = memberMapper.selectMembersPaged(requestDTO);

        long total = memberMapper.countMembers(requestDTO);

        return new PageResponseDTO<>(
                list,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total);
    }
}
