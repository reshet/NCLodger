package com.nclodger.dao;

import com.nclodger.myexception.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/30/13
 * Time: 2:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ConfirmationEmailDAOInterface {

    public boolean insert(ConfirmationEmail ConMail) throws MyException;
    public boolean update (ConfirmationEmail ConMail);
    public boolean delete(String Hash) throws MyException;
}
