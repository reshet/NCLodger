package com.nclodger.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 23.10.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
@Entity  //Этой аннотацией мы указываем, что данный класс является сущностью.
@Table(name = "UserType")
public class UserType {

    @Id
    @GeneratedValue
    @Column(name = "ID_UT")
    @NotNull
    private int id_ut;

    @Column(name = "NAME_T")
    @NotNull
    private String name_t;

    @OneToMany(mappedBy = "Users")
    private Set<Users> users;


    public UserType(){
    }

    public UserType(int id_ut, String name_t) {
        this.id_ut = id_ut;
        this.name_t = name_t;
    }



    public int getId_ut() {
        return id_ut;
    }

    public void setId_ut(int id_ut) {
        this.id_ut = id_ut;
    }

    public String getName_t() {
        return name_t;
    }

    public void setName_t(String name_t) {
        this.name_t = name_t;
    }




}
