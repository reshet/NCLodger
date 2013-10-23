package com.nclodger.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 09.10.13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
@Entity  //Этой аннотацией мы указываем, что данный класс является сущностью.
@Table(name = "Users")
public class Users implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    @NotNull
    private int id;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "PSWD")
    @NotNull
    private String pswd;

    @Column(name = "USERNAME")
    @NotNull
    private String username;

    @Column(name = "CONFIRM_REGISTER")
    @NotNull
    private int confirmRegister;

    @Column(name = "IS_BLOCKED")
    @NotNull
    private int is_blocked;

    @Column(name = "ID_CE")
    private int id_ce;
    @Column(name = "ID_TYPE")
    private int id_type;
    @Column(name = "ID_DE")
    private int id_de;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private UserType uType;

    public Users(){
    }

    public Users(int id, String email, String pswd,
                 String name, int confirmRegister, int is_blocked,
                 int id_de, int id_ce, int id_type)
    {
        this.id = id;
        this.email = email;
        this.username = name;
        this.pswd = pswd;
        this.confirmRegister = confirmRegister;
        this.is_blocked = is_blocked;
        this.id_de = id_de;
        this.id_ce = id_ce;
        this.id_type = id_type;
    }

    public Users(String email, String pswd, String username, int id_type, int is_blocked)
    {
       // this.id = id;
        this.email = email;
        this.username = username;
        this.pswd = pswd;
        this.is_blocked = is_blocked;
        this.id_type = id_type;
    }


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

    public void setName(String username){
        this.username = username;
    }

    public String getName(){
        return this.username;
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

    public int getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

    public int getId_ce() {
        return id_ce;
    }

    public void setId_ce(int id_ce) {
        this.id_ce = id_ce;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_de() {
        return id_de;
    }

    public void setId_de(int id_de) {
        this.id_de = id_de;
    }



}
