package com.nclodger.logic;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/7/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelCommissionDTO {
    private int smID;
    private double  smHotelCommission;

    public HotelCommissionDTO(int smID, double smHotelCommission){

        this.smID = smID;
        this.smHotelCommission = smHotelCommission;
    }



    public int getSmID() {
        return smID;
    }

    public void setSmID(int smID) {
        this.smID = smID;
    }

    public double getSmHotelCommission() {
        return smHotelCommission;
    }

    public void setSmHotelCommission(double smHotelCommission) {
        this.smHotelCommission = smHotelCommission;
    }
}
