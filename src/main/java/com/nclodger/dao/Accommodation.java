package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/22/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Accommodation {
    //default attribute of ACCOMMODATION
    private int id_acc;
    private int id_hotel;
    private double price;
    private int quantity;
    private String type;

    //addition info about accommodation
    private String city;
    private String country;

    public Accommodation(){

    }

    //constructor by default ALL Accommodation attribure
    public Accommodation(int id_acc,int id_hotel, double price, int quantity, String type){
        this.id_acc = id_acc;
        this.id_hotel = id_hotel;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    //constructor by default ALL Accommodation attribute
    //with additional information(country, city)
    public Accommodation(int id_acc,int id_hotel, double price, int quantity, String type,String city,String country){
        this.id_acc = id_acc;
        this.id_hotel = id_hotel;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.city = city;
        this.country = country;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
