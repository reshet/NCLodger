package com.nclodger.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 30.10.13
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class SManager {

    // PROPERTIES
    private int id;
    private int id_user;
    private double commission;
    private double vip_discount;
    private double user_discount;

    // CONSTRUCTORS
    public SManager(int id_user) {
        this.id_user = id_user;
    }

    public SManager(double commission, double vip_discount, double user_discount) {
        this.commission = commission;
        this.vip_discount = vip_discount;
        this.user_discount = user_discount;
    }

    public SManager(int id, int id_user) {
        this.id = id;
        this.id_user = id_user;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public double getCommission() {
        return commission;
    }

    public double getVip_discount() {
        return vip_discount;
    }

    public double getUser_discount() {
        return user_discount;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setVip_discount(double vip_discount) {
        this.vip_discount = vip_discount;
    }

    public void setUser_discount(double user_discount) {
        this.user_discount = user_discount;
    }
}
