package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 09.10.13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
public class User
{
    private int _id;
    private String _email;
    private String _pswd;
    private String _name;

    public User(int id, String email, String pswd, String name)
    {
        this._id = id;
        this._email = email;
        this._name = name;
        this._pswd = pswd;
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



}
