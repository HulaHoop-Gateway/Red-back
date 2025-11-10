package com.hulahoop.redback.transaction.model.dto;

public class TransactionDTO {
    private int transationNum;
    private String memberCode;
    private int amountUsed;
    private String paymentDate;
    private String status;
    private String startDate;
    private String endDate;
    private String merchantCode;

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
}
