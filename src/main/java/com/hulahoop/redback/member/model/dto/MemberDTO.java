package com.hulahoop.redback.member.model.dto;

/**
 * MemberDTO
 * 어드민 회원 관리 화면에서 표시할 회원 정보를 전달하는 DTO
 * - T_Member 테이블과 매핑 (camelCase 엄계)
 */
public class MemberDTO {
    private String memberCode; // 회원 고유 식별코드
    private String name; // 회원 이름
    private String id; // 로그인 아이디
    private String password; // BCrypt 해시된 비밀번호
    private String phoneNum; // 폰니본
    private String email; // 이메일 주소
    private String address; // 주소
    private String userType; // 회원 유형 (U=일반, A=관리자)
    private String notificationStatus;// 알림 수신 여부 (Y/N)

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
