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
                        "SELECT COUNT(*) FROM HOTEL WHERE INT_ID=?"
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
    public boolean insertHotel(final Hotel hotel) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO HOTEL(NAME_H,LOC_LAT,LOC_LNG,CATEGORY,CITY,COUNTRY,INT_ID) values (?,?,?,?,?,?,?)"
                );
                prep.setString(1, hotel.getName_hotel());
                prep.setDouble(2, hotel.getLoc_lat());
                prep.setDouble(3, hotel.getLoc_lng());
                prep.setInt(4, hotel.getCategory());
                prep.setString(5, hotel.getCity());
                prep.setString(6,hotel.getCountry());
                prep.setInt(7,hotel.getIntID());
                java.sql.ResultSet res = prep.executeQuery();
                res.next();

                return true;

            }
        });
    }
}
