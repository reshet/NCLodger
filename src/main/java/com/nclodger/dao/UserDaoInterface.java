package com.nclodger.dao;


import javax.naming.NamingException;
import java.sql.SQLException;
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
    public boolean getUser(String _email, String _password) throws SQLException, NamingException;
    public User find(int id);
}
