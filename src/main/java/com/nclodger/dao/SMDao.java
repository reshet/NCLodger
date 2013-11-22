package com.nclodger.dao;

import com.nclodger.myexception.MyException;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 16.11.13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public class SMDao implements SMDaoInterface{

    abstract class WrapperDBOperation<T> {
        abstract public T doMethod(Connection dataBase) throws MyException, SQLException;
    }

    private <T> T booleanOperation(WrapperDBOperation<T> operation) throws MyException {
        Connection dataBase = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/NCLodger");
            dataBase = ds.getConnection();
            return operation.doMethod(dataBase);
        } catch (SQLException e) {
            try {
                dataBase.rollback();
                throw new MyException(e.getMessage());
                //return null;
            } catch (SQLException e1) {
                throw new MyException(e1.getMessage());
            }
        } catch (NamingException e) {
            try {
                dataBase.rollback();
                throw new MyException(e.getMessage());
            } catch (SQLException e1) {
                throw new MyException(e1.getMessage());
            }
        } finally {
            try {
                dataBase.close();
            } catch (SQLException e) {
                throw new MyException(e.getMessage());
            }
        }
    }

    @Override
    public boolean insert(SManager smanager) throws MyException {
        return false;
    }

    @Override
    public boolean insert(int id_user) throws MyException {
        return false;
    }

    @Override
    public boolean delete(SManager smanager) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(int id_user) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getSManager(String email, String password) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SManager getSManager(int id) throws MyException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getSmanagerId(final String email) throws MyException {
        /**
         * Working sql query example:
         * SELECT ID_SM FROM MANAGER WHERE MANAGER.ID_USER IN
         *      (SELECT ID_USER FROM USERS WHERE USERS.EMAIL = 'reshet.ukr@gmail.com')
         */
        return booleanOperation(new WrapperDBOperation<Integer>() {
            @Override
            public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_SM FROM MANAGER WHERE MANAGER.ID_USER IN" +
                                "(SELECT ID_USER FROM USERS WHERE USERS.EMAIL = ?)"
                );
                prep.setString(1,email);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int idSm = res.getInt(1);
                return idSm;
            }
        });
    }

    // @Override
    public HashMap<Hotel,Integer> sortHotelbyPopular() throws MyException {
        return booleanOperation(new WrapperDBOperation<HashMap<Hotel,Integer>>() {
            //added sql request that return all atribute of HOTEL and total order for this hotel
            // sorting by decrease
            @Override
            public HashMap<Hotel,Integer> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM HOTEL"
                );

                java.sql.ResultSet results = prep.executeQuery();
                HashMap<Hotel,Integer> hotelsList = new HashMap<Hotel,Integer>();
                while (results.next()) {
                    int id_hotel = results.getInt(1);
                    String name_hotel = results.getString(2);
                    double loc_lat = results.getFloat(3);
                    double loc_lng = results.getFloat(4);
                    int category = results.getInt(5);
                    int id_sm = results.getInt(6);
                    String city = results.getString(7);
                    String country = results.getString(8);
                    int totalOrder = results.getInt(9);

                    Hotel h = new Hotel(id_hotel,name_hotel,loc_lat,loc_lng,category,id_sm,city,country);
                    hotelsList.put(h,totalOrder);
                }
                return hotelsList;
            }
        });
    }

    //the same as sortHotelbyPopular and timeframe
    public HashMap<Hotel,Integer> sortHotelbyPopularWithTimeFrame(Date start, Date end) throws MyException {
        return booleanOperation(new WrapperDBOperation<HashMap<Hotel,Integer>>() {

            @Override
            public HashMap<Hotel,Integer> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM HOTEL"
                );

                java.sql.ResultSet results = prep.executeQuery();
                HashMap<Hotel,Integer> hotelsList = new HashMap<Hotel,Integer>();
                while (results.next()) {
                    int id_hotel = results.getInt(1);
                    String name_hotel = results.getString(2);
                    double loc_lat = results.getFloat(3);
                    double loc_lng = results.getFloat(4);
                    int category = results.getInt(5);
                    int id_sm = results.getInt(6);
                    String city = results.getString(7);
                    String country = results.getString(8);
                    int totalOrder = results.getInt(9);

                    Hotel h = new Hotel(id_hotel,name_hotel,loc_lat,loc_lng,category,id_sm,city,country);
                    hotelsList.put(h,totalOrder);
                }
                return hotelsList;
            }
        });
    }


}

