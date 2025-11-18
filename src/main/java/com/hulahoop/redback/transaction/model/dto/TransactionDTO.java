package com.hulahoop.redback.transaction.model.dto;

public class TransactionDTO {

    private int transationNum;
    private String memberCode;

    private String phoneNum;
    private int amountUsed;
    private String paymentDate;
    private String status;
    private String startDate;
    private String endDate;
    private String merchantCode;

    // ⭐ 추가됨: 취소 거래일 경우 원본 거래번호
    private Integer originalTransationNum;

    public TransactionDTO() {
    }

    // 🔥 언더스코어 JSON → 카멜케이스 필드 자동 매핑 생성자
    public TransactionDTO(
            int transationNum,
            String memberCode,
            String phone_num,
            int amount_used,
            String payment_date,
            String status,
            String start_date,
            String end_date,
            String merchant_code
    ) {
        this.transationNum = transationNum;
        this.memberCode = memberCode;

        // ⭐ 언더스코어 JSON 필드를 DTO 변수에 매핑
        this.phoneNum = phone_num;
        this.amountUsed = amount_used;
        this.paymentDate = payment_date;
        this.status = status;
        this.startDate = start_date;
        this.endDate = end_date;
        this.merchantCode = merchant_code;
    }

    public int getTransationNum() {
        return transationNum;
    }

    public void setTransationNum(int transationNum) {
        this.transationNum = transationNum;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(int amountUsed) {
        this.amountUsed = amountUsed;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    // ⭐ 추가: 취소 시 원 거래번호 Getter/Setter
    public Integer getOriginalTransationNum() {
        return originalTransationNum;
    }

    public void setOriginalTransationNum(Integer originalTransationNum) {
        this.originalTransationNum = originalTransationNum;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transationNum=" + transationNum +
                ", memberCode='" + memberCode + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", amountUsed=" + amountUsed +
                ", paymentDate='" + paymentDate + '\'' +
                ", status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", originalTransationNum=" + originalTransationNum +
                '}';
    }
}
