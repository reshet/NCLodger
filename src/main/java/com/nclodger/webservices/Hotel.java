package com.nclodger.webservices;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/29/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hotel {
    private final Integer id;
    private final String name;
    private Double loc_lat;
    private Double loc_lng;

    public Hotel(Integer id, String name, Double loc_lat, Double loc_lng) {
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
}
