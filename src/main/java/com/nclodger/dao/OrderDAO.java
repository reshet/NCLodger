package com.nclodger.dao;

import com.nclodger.domain.Accommodation;
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
    public Boolean isExistHotelbyID(final int IntID) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(*) FROM HOTEL WHERE INT_ID=?"
                );
                prep.setInt(1, IntID);
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
    public Boolean insertHotel(final Hotel hotel) throws MyException {
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

    @Override
    public Boolean isExistAccbyID(final int expediaRoomID) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(*) FROM ACCOMMODATION WHERE EXPEDIA_ID=?"
                );
                prep.setInt(1, expediaRoomID);
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
    public Boolean insertAccommodation(final Accommodation acc) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO ACCOMMODATION(ID_HOTEL, PRICE, QUANTITY, TYPE, EXPEDIA_ID) VALUES (?,?,?,?,?)"
                );
                // public Accommodation(int id_hotel, double price, int quantity, String type, int roomExpediaID){
                prep.setInt(1,acc.getId_hotel());
                prep.setDouble(2,acc.getPrice());
                prep.setInt(3,acc.getQuantity());
                prep.setString(4,acc.getType());
                prep.setInt(5,acc.getRoomExpediaID());
                java.sql.ResultSet res = prep.executeQuery();
                res.next();

                return true;
            }
        });
    }

    @Override
    public Integer getIDHotelByintID(final int expediaHotelID) throws MyException {
        return dbOperation(new WrapperDBOperation<Integer>() {
            @Override
            public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT   ID_HOTEL FROM HOTEL WHERE INT_ID=?"
                );
                // public Accommodation(int id_hotel, double price, int quantity, String type, int roomExpediaID){
                prep.setInt(1,expediaHotelID);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int id = res.getInt(1);

                return id;
            }
        });
    }
}
