package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 24.11.13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class InitialDiscount {
    private int id_di;
    private double commission;
    private double vip_discount;
    private double user_discount;


    public InitialDiscount(){

    }

    public InitialDiscount(int id_di, double commission, double vip_discount, double user_discount){
        this.id_di = id_di;
        this.commission=commission;
        this.vip_discount=vip_discount;
        this.user_discount=user_discount;
    }


}
