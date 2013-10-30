package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 30.10.13
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class SManager {
    private int id;
    private int id_user;
    private Double commision;

    public SManager(int id, int id_user, Double commision) {
        this.id = id;
        this.id_user = id_user;
        this.commision = commision;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public Double getCommision() {
        return commision;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setCommision(Double commision) {
        this.commision = commision;
    }
}
