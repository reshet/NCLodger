package com.nclodger.domain;

import java.io.Serializable;

public class User implements Serializable {
    // PROPERTIES
    private int id;
    private String email;
    private String pswd;
    private String name;
    private int confirmRegister;
    private int id_ut;
    private int is_blocked;
    private Integer bonus;
    private int vip;

    // CONSTRUCTORS
    public User() {
    }

    public User(int id, String username) {
        this.id = id;
        this.name = username;
    }

    public User(String pswd, int id) {
        this.pswd = pswd;
        this.id = id;
    }

    public User(int id, String email, String pswd, String name, int confirmRegister) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pswd = pswd;
        this.confirmRegister = confirmRegister;
    }


    public User(String email, String pswd, String name, int confirmRegister) {
        //     this.id = id;
        this.email = email;
        this.name = name;
        this.pswd = pswd;
        this.confirmRegister = confirmRegister;
    }

    // GETTERS / SETTERS

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getId_ut() {
        return id_ut;
    }

    public void setId_ut(int id_ut) {
        this.id_ut = id_ut;
    }

    public int getIs_blocked() {
        return this.is_blocked;
    }

    public void setIs_blocked(int block) {
        this.is_blocked = block;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return this.pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getConfirmRegister() {
        return this.confirmRegister;
    }

    public void set_confirm_register(int confirmRegister) {
        this.confirmRegister = confirmRegister;
    }

}
