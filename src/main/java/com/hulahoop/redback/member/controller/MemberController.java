package com.hulahoop.redback.member.controller;

// 관리자 대시보드 회원 관리 페이지 API 엔드포인트
// 전체 목록 조회(하위 호환)와 페이징+검색 조회 두 가지 방식을 함께 제공한다
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.member.model.dto.MemberDTO;
import com.hulahoop.redback.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // GET /api/members/all
    // 기존 스펙 호환을 위해 limit 없이 전체 회원을 반환
    @GetMapping("/all")
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // GET /api/members?page=&size=&keyword=
    // 페이징 및 키워드 필터를 지원하는 회원 목록 조회
    @GetMapping
    public ResponseEntity<PageResponseDTO<MemberDTO>> getMembers(PageRequestDTO requestDTO) {

        PageResponseDTO<MemberDTO> result = memberService.getMembers(requestDTO);

        return ResponseEntity.ok(result);
    }
}
