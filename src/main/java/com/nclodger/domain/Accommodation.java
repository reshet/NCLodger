package com.nclodger.domain;

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
    private int roomExpediaID;



    public Accommodation(){

    }

    //constructor by default  Accommodation attribure
    public Accommodation(int id_acc,int id_hotel, double price, int quantity, String type){
        this.id_acc = id_acc;
        this.id_hotel = id_hotel;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public Accommodation(int id_hotel, double price, int quantity, String type, int roomExpediaID){
        this.id_hotel = id_hotel;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.roomExpediaID = roomExpediaID;
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

    public int getRoomExpediaID() {
        return roomExpediaID;
    }

    public void setRoomExpediaID(int roomExpediaID) {
        this.roomExpediaID = roomExpediaID;
    }
}
