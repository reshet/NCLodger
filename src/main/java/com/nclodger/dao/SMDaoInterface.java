package com.nclodger.dao;

import com.nclodger.myexception.MyException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 30.10.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface SMDaoInterface {
    public boolean insert(SManager smanager) throws MyException;
    public boolean insert(int id_user) throws MyException;
    public boolean delete(SManager smanager) throws MyException;
    public boolean delete(int id_user) throws MyException;
    public boolean getSManager(String email, String password) throws MyException;
    public SManager getSManager(int id) throws MyException;
    public int getSmanagerId(String email) throws MyException;
    public List<String> sortHotelbyPopular() throws MyException;
}