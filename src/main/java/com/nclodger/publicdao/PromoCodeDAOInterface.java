package com.nclodger.publicdao;

import com.nclodger.domain.PromoCode;
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
    public boolean isExist(final String code) throws MyException;
    public boolean isUsed(final String code) throws MyException;
    public boolean isExpired(final String code) throws MyException;
    public PromoCode get(final String code) throws MyException;


}
