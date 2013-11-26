package com.nclodger.dao;

import com.nclodger.additional.AccommodationTotalValue;
import com.nclodger.additional.HotelTotalOrder;
import com.nclodger.domain.Accommodation;
import com.nclodger.domain.Hotel;
import com.nclodger.domain.SManager;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.SMDaoInterface;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 16.11.13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
@Component("smdao")
public class SMDAO extends AbstractRepository implements SMDaoInterface {

    @Override
    public boolean insert(SManager smanager) throws MyException {
        return false;
    }

    @Override
    public boolean insert(final int id_user) throws MyException {
          return dbOperation(new WrapperDBOperation<Boolean>() {
              @Override
              public Boolean doMethod(Connection dataBase) throws SQLException, MyException {

                  PreparedStatement prep = dataBase.prepareStatement(
                          "UPDATE USERS SET ID_UT=? WHERE ID_USER=?"
                  );
                  prep.setInt(1, 2);
                  prep.setInt(2, id_user);
                  java.sql.ResultSet res = prep.executeQuery();
                  res.next();


                  PreparedStatement prep2 = dataBase.prepareStatement(
                          "SELECT COMMISSION, VIP_DISCOUNT, USER_DISCOUNT FROM INITIAL_DISCOUNT WHERE ID_ID=?"
                  );

                  prep2.setInt(1, 1);
                  java.sql.ResultSet res2 = prep2.executeQuery();
                  res2.next();

                  double commission = res2.getDouble(1);
                  double vip_discount = res2.getDouble(2);
                  double user_discount = res2.getDouble(3);

         /*
                PreparedStatement prep2h = dataBase.prepareStatement(
                        "SELECT MAX(ID_SM) FROM MANAGER"
                ) ;
                java.sql.ResultSet res2h = prep2h.executeQuery();
                res2h.next();
                int maxId = res2h.getInt(1);

          */
                  PreparedStatement prep3 = dataBase.prepareStatement(
                          "INSERT INTO MANAGER(ID_USER,COMMISSION,VIP_DISCOUNT,USER_DISCOUNT,ID_ID)" +
                                  "VALUES" +
                                  "(?,?,?,?,1)"
                  );

                  //    maxId=maxId+1;

                  //        prep3.setInt(1,id_user);
                  prep3.setInt(1, id_user);
                  prep3.setDouble(2, commission);
                  prep3.setDouble(3, vip_discount);
                  prep3.setDouble(4, user_discount);

                  java.sql.ResultSet res3 = prep3.executeQuery();
                  res3.next();

                  return true;

              }
          });
    }



    @Override
    public boolean delete(SManager smanager) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(final int id_user) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement first = dataBase.prepareStatement(
                        "UPDATE USERS SET ID_UT=? WHERE ID_USER=?"
                );
                first.setInt(1, 1);
                first.setInt(2, id_user);
                java.sql.ResultSet firstRes = first.executeQuery();
                firstRes.next();


                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_SM FROM MANAGER WHERE ID_USER=?"
                );
                prep.setInt(1, id_user);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int id_sm = res.getInt(1);

                //теперь нужно удалить все записи этого менеджеришки в подчиненных таблицах

                PreparedStatement prep2 = dataBase.prepareStatement(
                        "DELETE FROM PROMOCODE WHERE ID_SM=?"
                );
                prep2.setInt(1, id_sm);
                prep2.executeUpdate();


                PreparedStatement prep3 = dataBase.prepareStatement(
                        "DELETE FROM ORDERS WHERE ID_SM=?"
                );
                prep3.setInt(1, id_sm);
                prep3.executeUpdate();


                PreparedStatement prep4 = dataBase.prepareStatement(
                        "DELETE FROM HOTEL_MANAGER WHERE ID_SM=?"
                );
                prep4.setInt(1, id_sm);
                prep4.executeUpdate();

                //и наконец удалим самого менеджера

                PreparedStatement prep5 = dataBase.prepareStatement(
                        "DELETE FROM MANAGER WHERE ID_SM=?"
                );
                prep5.setInt(1, id_sm);
                prep5.executeUpdate();

                return true;
            }
        });
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
        return dbOperation(new WrapperDBOperation<Integer>() {
            @Override
            public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_SM FROM MANAGER WHERE MANAGER.ID_USER IN" +
                                "(SELECT ID_USER FROM USERS WHERE USERS.EMAIL = ?)"
                );
                prep.setString(1, email);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int idSm = res.getInt(1);
                return idSm;
            }
        });
    }

    @Override
    public boolean setCommAndDiscounts(final int id_sm, final double commission,
              final double vip_discount, final double user_discount) throws MyException {

        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE MANAGER SET COMMISSION=?,VIP_DISCOUNT=?,USER_DISCOUNT=?  WHERE ID_SM=?"
                );

                prep.setDouble(1, commission);
                prep.setDouble(2, vip_discount);
                prep.setDouble(3, user_discount);
                prep.setInt(4, id_sm);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });

    }


    //add sql request that return all atribute of HOTEL and total order for this hotel
    // sorting by decrease
    @Override
    public ArrayList<HotelTotalOrder> sortHotelbyPopular() throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<HotelTotalOrder>>() {

            @Override
            public ArrayList<HotelTotalOrder> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM HOTEL"
                );

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<HotelTotalOrder> hotelsList = new ArrayList<HotelTotalOrder>();
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

                    Hotel h = new Hotel(id_hotel, name_hotel, loc_lat, loc_lng, category, id_sm, city, country);
                    hotelsList.add(new HotelTotalOrder(h, totalOrder));
                }
                return hotelsList;
            }
        });
    }

    //the same as sortHotelbyPopular but with  timeframe
    @Override
    public ArrayList<HotelTotalOrder> sortHotelbyPopularWithTimeFrame(Date start, Date end) throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<HotelTotalOrder>>() {

            @Override
            public ArrayList<HotelTotalOrder> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM HOTEL"
                );

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<HotelTotalOrder> hotelsList = new ArrayList<HotelTotalOrder>();
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

                    Hotel h = new Hotel(id_hotel, name_hotel, loc_lat, loc_lng, category, id_sm, city, country);
                    hotelsList.add(new HotelTotalOrder(h, totalOrder));
                }
                return hotelsList;
            }
        });
    }


    @Override
    public ArrayList<AccommodationTotalValue> sortAccommodationbyValuableWithTimeFrame(Date start, Date end) throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<AccommodationTotalValue>>() {

            @Override
            public ArrayList<AccommodationTotalValue> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM ACCOMODATION"
                );

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<AccommodationTotalValue> accList = new ArrayList<AccommodationTotalValue>();
                while (results.next()) {
                    int id_acc = results.getInt(1);
                    int id_hotel = results.getInt(2);
                    double price = results.getDouble(3);
                    int quantity = results.getInt(4);
                    String type = results.getString(5);
                    String hotel_name = results.getString(6);
                    String city = results.getString(7);
                    String coutry = results.getString(8);

                    double totalValue = results.getDouble(9);


                    Accommodation acc = new Accommodation(id_acc, id_hotel, price, quantity, type);
                    accList.add(new AccommodationTotalValue(acc, hotel_name, city, coutry, totalValue));
                }
                return accList;
            }
        });
    }
}

