package com.nclodger.logic;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/7/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelDiscountDTO {
    private int smID;
    private String smName;
    private double smHotelUserDisc;
    private double smHotelVipDisc;

    public HotelDiscountDTO(int smID, double smHotelUserDisc,double smHotelVipDisc,String smname){

        this.smID = smID;
        this.smHotelUserDisc = smHotelUserDisc;
        this.smHotelVipDisc = smHotelVipDisc;
        this.setSmName(smname);
    }



    public int getSmID() {
        return smID;
    }

    public void setSmID(int smID) {
        this.smID = smID;
    }

    public double getSmHotelUserDisc() {
        return smHotelUserDisc;
    }

    public void setSmHotelUserDisc(double smHotelUserDisc) {
        this.smHotelUserDisc = smHotelUserDisc;
    }

    public double getSmHotelVipDisc() {
        return smHotelVipDisc;
    }

    public void setSmHotelVipDisc(double smHotelVipDisc) {
        this.smHotelVipDisc = smHotelVipDisc;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }
}
