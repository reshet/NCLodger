package com.nclodger.dao;

public class PromoCode {
    private int id_pc;
    private String code;
    private String start_date;
    private String end_date;
    private double discount;
    private int status;
    private int id_sm;

    public PromoCode(String code, String start_date, String end_date, double discount, int status, int id_sm) {
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        this.status = status;
        this.id_sm = id_sm;
    }

    public PromoCode(int id_pc, String code, String start_date, String end_date, double discount, int status) {
        this.id_pc = id_pc;
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        this.status = status;
    }

    public int getId_pc() {
        return id_pc;
    }

    public void setId_pc(int id_pc) {
        this.id_pc = id_pc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId_sm() {
        return id_sm;
    }

    public void setId_sm(int id_sm) {
        this.id_sm = id_sm;
    }
}
