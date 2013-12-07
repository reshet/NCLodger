package com.nclodger.domain;

import com.nclodger.webservices.HotelDTO;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/4/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Order {
    private HotelDTO h;
    private int userid;
    private PromoCode promo;
    private double final_price;
    private String start_date;
    private String end_date;

    public HotelDTO getH() {
        return h;
    }

    public void setH(HotelDTO h) {
        this.h = h;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public PromoCode getPromo() {
        return promo;
    }

    public void setPromo(PromoCode promo) {
        this.promo = promo;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
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
}
