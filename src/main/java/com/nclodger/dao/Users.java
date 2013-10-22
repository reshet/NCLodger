package com.nclodger.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 09.10.13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
@Entity  //Этой аннотацией мы указываем, что данный класс является сущностью.
public class Users implements Serializable {
    private int id;
    private String email;
    private String pswd;
    private String name;
    private int confirmRegister;
    //private int is_blocked;

    public Users(){
    }

    public Users(int id, String email, String pswd, String name, int confirmRegister)
    {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pswd = pswd;
        this.confirmRegister = confirmRegister;
        // this._is_blocked = is_blocked;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }


    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getPswd(){
        return this.pswd;
    }

    public void setPswd(String pswd){
        this.pswd = pswd;
    }

    public int get_confirmRegister(){
        return this.confirmRegister;
    }

    public void set_confirm_register(int confirmRegister){
        this.confirmRegister = confirmRegister;
    }



}
