package com.nclodger.additional;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/9/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelManagingInfo {
    private int idsm;
    private int idHotel;
    private String hotelName;
    private String city;
    private String country;
    private int commission;

    public HotelManagingInfo(){

    }

    public HotelManagingInfo(int idsm,int idHotel,String hotelName,String city,
                             String country,int commission){
        this.idsm = idsm;
        this.idHotel = idHotel;
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.commission = commission;
    }

    public int getIdsm() {
        return idsm;
    }

    public void setIdsm(int idsm) {
        this.idsm = idsm;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
}
