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
    public boolean insert(Users _user);
    public boolean update (Users _user);
    public boolean delete(Users _user) throws ClassNotFoundException, SQLException;
    public boolean getUser(String _email, String _password) throws SQLException, NamingException, ClassNotFoundException;
    public Users find(int id) throws ClassNotFoundException, SQLException;       //+
    public boolean confirmRegister(Users _user) throws Exception;  //+
}
