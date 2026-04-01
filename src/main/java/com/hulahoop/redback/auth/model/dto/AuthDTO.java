package com.hulahoop.redback.auth.model.dto;

/**
 * AuthDTO
 * 관리자 인증에 필요한 정보를 담는 데이터 전송 객체
 * - T_Member 테이블에서 user_type='A' 조건으로 조회한 관리자 로우와 매핑함
 */
public class AuthDTO {

    private String member_code; // 회원 고유 식별코드
    private String name; // 관리자 이름
    private String id; // 로그인 아이디
    private String password; // BCrypt 해시 저장된 비밀번호
    private String phone_num; // 연락전화번호
    private String email; // 이메일 주소
    private String address; // 주소
    private String user_type; // 회원 유형 (A=관리자, U=일반회원)

    public AuthDTO() {
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
