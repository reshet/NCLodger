package com.nclodger.domain;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/22/13
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hotel {
    private int id_hotel;
    private String name_hotel;
    private double loc_lat;
    private double loc_lng;
    private int category;
    private int id_sm;
    private String city;
    private String country;
    private int intID;

    public Hotel(){
    }

    public Hotel(String name_hotel, String country, String city, int category, double loc_lng, double loc_lat,int intID) {
        this.name_hotel = name_hotel;
        this.country = country;
        this.city = city;
        this.loc_lng = loc_lng;
        this.loc_lat = loc_lat;
        this.category = category;
        this.intID = intID;
    }

    public Hotel(int id_hotel,String name_hotel, double loc_lat,double loc_lng,
                  int category, int id_sm, String city, String country){
        this.id_hotel = id_hotel;
        this.name_hotel = name_hotel;
        this.loc_lat = loc_lat;
        this.loc_lng = loc_lng;
        this.category = category;
        this.id_sm = id_sm;
        this.city = city;
        this.country = country;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getName_hotel() {
        return name_hotel;
    }

    public void setName_hotel(String name_hotel) {
        this.name_hotel = name_hotel;
    }

    public double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getId_sm() {
        return id_sm;
    }

    public void setId_sm(int id_sm) {
        this.id_sm = id_sm;
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

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }
}
