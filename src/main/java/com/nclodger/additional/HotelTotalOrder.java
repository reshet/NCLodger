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
    private Hotel h;
    private int totalOrder;

    public HotelTotalOrder(){

    }

    public HotelTotalOrder(Hotel h, int totalOrder){
        this.h = h;
        this.totalOrder = totalOrder;
    }

    public Hotel getH() {
        return h;
    }

    public void setH(Hotel h) {
        this.h = h;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
}
