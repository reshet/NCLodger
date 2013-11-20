package com.nclodger.dao;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/16/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class PromoCode {
    private int id_pc;
    private String code;
    private Date start_date;
    private Date end_date;
    private double discount;
    private int isUsed = 0;
    private int id_sm;

    public PromoCode(String code,Date start_date, Date end_date, double discount){
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
    }

    public PromoCode(int id_pc, String code, Date start_date, Date end_date, double discount, int used) {
        this.id_pc = id_pc;
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        isUsed = used;
    }

    public PromoCode(int id_pc,String code,Date start_date, Date end_date, double discount,int isUsed, int id_sm){
        this.id_pc = id_pc;
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        this.isUsed = isUsed;
        this.id_sm = id_sm;
    }
    public PromoCode(){

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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getUsed() {
        return isUsed;
    }

    public void setUsed(int used) {
        isUsed = used;
    }

    public int getId_sm() {
        return id_sm;
    }

    public void setId_sm(int id_sm) {
        this.id_sm = id_sm;
    }
}
