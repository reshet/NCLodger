package com.nclodger.dao;

import com.nclodger.myexception.MyException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/16/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PromoCodeDAOInterface {
    public boolean insert(PromoCode pc) throws MyException;
    public List<PromoCode> getAllPCbySMid(int id_sm) throws MyException;
}
