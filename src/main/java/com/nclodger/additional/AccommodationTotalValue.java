package com.nclodger.additional;

import com.nclodger.domain.Accommodation;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/22/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccommodationTotalValue {

    //addition info about accommodation
    private String type;
    private String hotel_name;
    private String city;
    private String country;
    private double totalValue;


    public AccommodationTotalValue(){

    }

    public AccommodationTotalValue(String type,String hotel_name,String city,String country,double totalValue){
        this.type = type;
        this.hotel_name = hotel_name;
        this.city = city;
        this.country = country;
        this.totalValue = totalValue;
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

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
