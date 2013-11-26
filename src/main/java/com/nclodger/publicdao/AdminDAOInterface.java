package com.nclodger.publicdao;

import com.nclodger.myexception.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 23.11.13
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public interface AdminDAOInterface {
    public boolean update (double commission, double vip_disc, double user_disc) throws MyException;
}
