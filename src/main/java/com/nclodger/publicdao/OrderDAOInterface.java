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
    public Boolean isExistHotelbyID (int IntID) throws MyException;
    public Boolean insertHotel (Hotel hotel) throws  MyException;
    public Boolean isExistAccbyID(int expediaRoomID) throws MyException;
    public Boolean insertAccommodation(Accommodation acc) throws MyException;
    public Integer getIDHotelByintID(int expediaHotelID) throws MyException;
    public Integer getIDAccByExpID(int expediaID) throws MyException;
    public Boolean isExistOrderOnAcc(int accID) throws MyException;
    public Boolean isFreeAcc(int accID,String startDate,String endDate) throws MyException;

}
