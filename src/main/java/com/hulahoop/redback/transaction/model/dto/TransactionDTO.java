package com.hulahoop.redback.transaction.model.dto;

public class TransactionDTO {
    private int transation_num;
    private String member_code;
    private int amount_used;
    private String payment_date;
    private int status;
    private String start_date;
    private String end_date;
    private String merchant_code;

    public int getTransation_num() {
        return transation_num;
    }
    public void setTransation_num(int transation_num) {
        this.transation_num = transation_num;
    }
    public String getMember_code() {
        return member_code;
    }
    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }
    public int getAmount_used() {
        return amount_used;
    }
    public void setAmount_used(int amount_used) {
        this.amount_used = amount_used;
    }
    public String getPayment_date() {
        return payment_date;
    }
    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public String getMerchant_code() {
        return merchant_code;
    }
    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }
}
