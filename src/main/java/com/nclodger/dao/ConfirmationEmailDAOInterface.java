package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/30/13
 * Time: 2:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ConfirmationEmailDAOInterface {

    public boolean insert(ConfirmationEmail conMail);
    public boolean update (ConfirmationEmail conMail);
    public boolean delete(ConfirmationEmail conMail);
}
