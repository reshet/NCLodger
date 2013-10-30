package com.nclodger.dao;

import com.nclodger.myexception.MyException;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 30.10.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface SMDaoInterface {
    public boolean insert(SManager smanager) throws MyException;
    public boolean update (SManager smanager);
    public boolean delete(SManager smanager) throws ClassNotFoundException, SQLException;
    public boolean getSManager(String email, String password) throws SQLException, NamingException, ClassNotFoundException, MyException;
    public SManager find(int id) throws ClassNotFoundException, SQLException, MyException;
}