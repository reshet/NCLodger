package com.nclodger.publicdao;

import com.nclodger.domain.Accommodation;
import com.nclodger.domain.Hotel;
import com.nclodger.myexception.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/4/13
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OrderDAOInterface {
    public Boolean isExistHotelbyID (int idHotel) throws MyException;
    public Boolean insertHotel (Hotel hotel) throws  MyException;
    public Boolean isExistAccbyID(int expediaRoomID) throws MyException;
    public Boolean insertAccommodation(Accommodation acc) throws MyException;

}
