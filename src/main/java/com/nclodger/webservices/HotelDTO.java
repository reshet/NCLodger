package com.nclodger.webservices;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/29/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelDTO implements Serializable {
    private final Integer id;
    private final String name;
    private Double loc_lat;
    private Double loc_lng;
    private String image_url;
    private String address;
    private String price;
    private String roomType;
    private String roomOccupancy;
    private Double roomPrice;


    public HotelDTO(Integer id, String name, Double loc_lat, Double loc_lng) {
        this.id = id;
        this.name = name;
        this.loc_lat = loc_lat;
        this.loc_lng = loc_lng;
    }
    @Override
    public String toString(){
       return "ID: "+ getId() +", name: "+ getName()+", location: "+loc_lat+","+loc_lng;
    }

    public Double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(Double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public Double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(Double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomOccupancy(String roomOccupancy){
        this.roomOccupancy = roomOccupancy;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomOccupancy() {
        return roomOccupancy;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }
}
