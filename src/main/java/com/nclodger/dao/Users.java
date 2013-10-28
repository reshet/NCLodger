package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 09.10.13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
public class Users
{
    private int _id;
    private String _email;
    private String _pswd;
    private String _name;
    private int _confirm_register;

    public Users(int id, String email, String pswd, String name, int confirm_register)
    {
        this._id = id;
        this._email = email;
        this._name = name;
        this._pswd = pswd;
        this._confirm_register = confirm_register;
    }


    public void setId(int id)
    {
        this._id = id;
    }

    public int getId()
    {
        return this._id;
    }

    public void setEmail(String email)
    {
        this._email = email;
    }

    public String getEmail()
    {
        return this._email;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getName()
    {
        return this._name;
    }

    public String getPswd()
    {
        return this._pswd;
    }

    public void setPswd(String pswd)
    {
        this._pswd = pswd;
    }

    public int get_confirm_register()
    {
        return this._confirm_register;
    }

    public void set_confirm_register(int _confirm_register)
    {
        this._confirm_register = _confirm_register;
    }

}
