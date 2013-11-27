package com.nclodger.additional;

import com.nclodger.domain.Hotel;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/24/13
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelTotalOrder {
    private String hotelname;
    private String city;
    private String country;
    private int totalOrder;

    public HotelTotalOrder(){

    }

    public HotelTotalOrder(String hotelname,String city,String country,int totalOrder){
        this.hotelname = hotelname;
        this.city = city;
        this.country = country;
        this.totalOrder =totalOrder;

    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
