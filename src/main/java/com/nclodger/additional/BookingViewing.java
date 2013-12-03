package com.nclodger.additional;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/3/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookingViewing {
    private String type;
    private String hotelName;
    private String hotelCity;
    private String dateOrder;
    private String startOrder;
    private String endOrder;
    private double price;

    public BookingViewing(){

    }

    public BookingViewing(String type,String hotelName,String hotelCity, String dateOrder,
                          String startOrder, String endOrder,double price){
        this.type = type;
        this.hotelCity = hotelCity;
        this.hotelName = hotelName;
        this.dateOrder = dateOrder;
        this.startOrder = startOrder;
        this.endOrder = endOrder;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(String startOrder) {
        this.startOrder = startOrder;
    }

    public String getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(String endOrder) {
        this.endOrder = endOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
