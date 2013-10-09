package com.nclodger.dao;


import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface UserDaoInterface
{
    public void insert(User _user);
    public void update (User _user);
    public void delete(User _user);
    public User getUser(String _email, String _password);
    public User find(int id);
    public boolean existUser(String _name, String _password);
}
