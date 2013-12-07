package com.nclodger.dao;

import com.nclodger.domain.Hotel;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.OrderDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/7/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderDAO extends AbstractRepository implements OrderDAOInterface {
    @Override
    public boolean isExistHotelbyID(final int idHotel) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(*) FROM HOTEL WHERE HOTEL.ID_HOTEL=?"
                );
                prep.setInt(1, idHotel);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                boolean isExist = false;
                if(res.getInt(1)!=0){
                    isExist = true;
                }
                return isExist;

            }
        });
    }

    @Override
    public boolean insertHotel(Hotel hotel) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
