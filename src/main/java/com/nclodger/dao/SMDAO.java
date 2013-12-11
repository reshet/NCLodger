package com.nclodger.dao;

import com.nclodger.additional.AccommodationTotalValue;
import com.nclodger.additional.HotelManagingInfo;
import com.nclodger.additional.HotelTotalOrder;
import com.nclodger.domain.SManager;
import com.nclodger.logic.HotelCommissionDTO;
import com.nclodger.logic.HotelDiscountDTO;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.SMDaoInterface;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Integer getCommission(final Integer idSm) throws MyException {
        return dbOperation(new WrapperDBOperation<Integer>() {
            @Override
            public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COMMISSION FROM MANAGER WHERE ID_SM=?"
                );
                prep.setInt(1, idSm);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return res.getInt(1);
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
                        "SELECT HOTEL.NAME_H,HOTEL.CITY,HOTEL.CITY,TOTALORDER FROM " +
                                "(SELECT ID_HOTEL as HOT, COUNT(ID_ORDER) AS TOTALORDER FROM ( " +
                                "         SELECT ORDERS.ID_ORDER ,ACCOMMODATION.id_acc,HOTEL.id_hotel,HOTEL.NAME_H FROM ORDERS " +
                                "         LEFT JOIN ACCOMMODATION " +
                                "              ON ORDERS.ID_ACC=ACCOMMODATION.ID_ACC " +
                                "              LEFT JOIN HOTEL " +
                                "                    ON ACCOMMODATION.ID_HOTEL=HOTEL.id_hotel " +
                                "                    )" +
                                " GROUP BY ID_HOTEL  ),HOTEL" +
                        " WHERE HOTEL.ID_HOTEL=HOT " +
                         " ORDER BY TOTALORDER DESC"
                );

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<HotelTotalOrder> hotelsList = new ArrayList<HotelTotalOrder>();
                while (results.next()) {
                    String name_hotel = results.getString(1);
                    String city = results.getString(2);
                    String country = results.getString(3);
                    int totalorder = results.getInt(4);
                    HotelTotalOrder h = new HotelTotalOrder(name_hotel,city,country,totalorder);
                    hotelsList.add(h);
                }
                return hotelsList;
            }
        });
    }

    @Override
    public SManager getCurrentCommAndDiscounts(final String email) throws MyException {
        return dbOperation(new WrapperDBOperation<SManager>() {
            @Override
            public SManager doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COMMISSION,VIP_DISCOUNT,USER_DISCOUNT FROM MANAGER WHERE MANAGER.ID_USER IN" +
                                "(SELECT ID_USER FROM USERS WHERE USERS.EMAIL = ?)"
                );
                prep.setString(1, email);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                SManager sm = new SManager(res.getDouble(1),res.getDouble(2),res.getDouble(3));
                return sm;
            }
        });

    }

    //the same as sortHotelbyPopular but with  timeframe
    @Override
    public ArrayList<HotelTotalOrder> sortHotelbyPopularWithTimeFrame(final String start,final String end) throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<HotelTotalOrder>>() {

            @Override
            public ArrayList<HotelTotalOrder> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT HOTEL.NAME_H,HOTEL.CITY,HOTEL.CITY,TOTALORDER FROM " +
                                "       (SELECT ID_HOTEL as HOT, COUNT(ID_ORDER) AS TOTALORDER FROM ( " +
                                "                SELECT ORDERS.ID_ORDER ,ACCOMMODATION.id_acc,HOTEL.id_hotel,HOTEL.NAME_H FROM ORDERS " +
                                "                LEFT JOIN ACCOMMODATION " +
                                "                ON ORDERS.ID_ACC=ACCOMMODATION.ID_ACC " +
                                "                LEFT JOIN HOTEL " +
                                "                ON ACCOMMODATION.ID_HOTEL=HOTEL.id_hotel " +
                                "                WHERE TRUNC(ORDERS.date_order) >= TO_DATE(?,'mm/dd/yy') AND " +
                                "                      TRUNC(ORDERS.date_order) < TO_DATE(?,'mm/dd/yy')) " +
                                "                GROUP BY ID_HOTEL  ),HOTEL " +
                                " WHERE HOTEL.ID_HOTEL=HOT" +
                                " ORDER BY TOTALORDER DESC"
                );

                prep.setString(1, start);
                prep.setString(2, end);

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<HotelTotalOrder> hotelsList = new ArrayList<HotelTotalOrder>();
                while (results.next()) {
                    String name_hotel = results.getString(1);
                    String city = results.getString(2);
                    String country = results.getString(3);
                    int totalorder = results.getInt(4);
                    HotelTotalOrder h = new HotelTotalOrder(name_hotel,city,country,totalorder);
                    hotelsList.add(h);
                }
                return hotelsList;
            }
        });
    }


    @Override
    public ArrayList<AccommodationTotalValue> sortAccommodationbyValuableWithTimeFrame(final String start, final String end) throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<AccommodationTotalValue>>() {

            @Override
            public ArrayList<AccommodationTotalValue> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ACCTYPE,HOTEL.NAME_H,HOTEL.CITY,HOTEL.COUNTRY,TOTALVALUE FROM ( " +
                                "  SELECT ACCOMMODATION.id_hotel AS ACCHOTID,ACCOMMODATION.TYPE AS ACCTYPE,ACCOMMODATION.ID_ACC,TOTACC,TOTALVALUE FROM ( " +
                                "    SELECT ACCOMMODATION.ID_ACC AS TOTACC, SUM(ORDERS.PRICE) AS TOTALVALUE FROM ORDERS " +
                                "      LEFT JOIN ACCOMMODATION " +
                                "        ON ORDERS.ID_ACC=ACCOMMODATION.ID_ACC " +
                                "        WHERE TRUNC(ORDERS.date_order) >= TO_DATE(?,'mm/dd/yy') AND " +
                                "              TRUNC(ORDERS.date_order) < TO_DATE(?,'mm/dd/yy') " +
                                "    GROUP BY ACCOMMODATION.ID_ACC),ACCOMMODATION " +
                                "  WHERE ACCOMMODATION.ID_ACC=TOTACC " +
                                " ),HOTEL" +
                                " WHERE HOTEL.ID_HOTEL=ACCHOTID " +
                                " ORDER BY TOTALVALUE DESC "
                );
                prep.setString(1, start);
                prep.setString(2, end);
                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<AccommodationTotalValue> accList = new ArrayList<AccommodationTotalValue>();
                while (results.next()) {
                    String type = results.getString(1);
                    String hotel_name = results.getString(2);
                    String city = results.getString(3);
                    String coutry = results.getString(4);
                    double totalValue = results.getDouble(5);

                    AccommodationTotalValue acc = new AccommodationTotalValue(type,hotel_name,city,coutry,totalValue);
                    accList.add(acc);
                }
                return accList;
            }
        });
    }

    @Override
    public List<HotelCommissionDTO> getHotelCommissions(final int hotel_exp_id) throws MyException {
        return dbOperation(new WrapperDBOperation<List<HotelCommissionDTO>>() {

            @Override
            public List<HotelCommissionDTO> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT hm.COMMISSION, hm.ID_SM FROM HOTEL h " +
                                "INNER JOIN HOTEL_MANAGER hm ON hm.ID_HOTEL = h.ID_HOTEL" +
                                " WHERE h.INT_ID = ?" +
                                " ORDER BY hm.COMMISSION ASC "
                );
                prep.setInt(1, hotel_exp_id);
                //prep.setString(2, end);
                java.sql.ResultSet results = prep.executeQuery();
                List<HotelCommissionDTO> accList = new ArrayList<HotelCommissionDTO>();
                while (results.next()) {
                    Integer commission = results.getInt(1);
                    Integer smId = results.getInt(2);

                    accList.add(new HotelCommissionDTO(smId,commission));
                }
                return accList;

            }
        });
    }

    @Override
    public List<HotelDiscountDTO> getHotelDiscounts(final int hotel_exp_id) throws MyException {
        return dbOperation(new WrapperDBOperation<List<HotelDiscountDTO>>() {

            @Override
            public List<HotelDiscountDTO> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT m.USER_DISCOUNT, m.VIP_DISCOUNT, hm.ID_SM FROM HOTEL h " +
                                "INNER JOIN HOTEL_MANAGER hm " +
                                "INNER JOIN MANAGER m ON hm.ID_HOTEL = h.ID_HOTEL AMD hm.ID_SM = m.ID_SM" +
                                " WHERE h.INT_ID = ?" +
                                " ORDER BY m.USER_DISCOUNT ASC "
                );
                prep.setInt(1, hotel_exp_id);
                //prep.setString(2, end);
                java.sql.ResultSet results = prep.executeQuery();
                List<HotelDiscountDTO> accList = new ArrayList<HotelDiscountDTO>();
                while (results.next()) {
                    Integer userdisc = results.getInt(1);
                    Integer vipdisc = results.getInt(2);

                    Integer smId = results.getInt(3);


                    accList.add(new HotelDiscountDTO(smId,userdisc,vipdisc));
                }
                return accList;

            }
        });
    }

    @Override
    public Boolean isOccupied(final Integer id_sm, final Integer id_hotel) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(*) FROM HOTEL_MANAGER WHERE ID_SM=? AND ID_HOTEL=?"
                );
                prep.setInt(1, id_sm);
                prep.setInt(2, id_hotel);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int count = res.getInt(1);
                if(count == 0) {
                    return false;
                }
                else {
                    return true;
                }
            }
        });
    }

    @Override
    public Integer getIdHotelByIdDTO(final Integer idDTO) throws MyException {
        return dbOperation(new WrapperDBOperation<Integer>() {
            @Override
            public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_HOTEL FROM HOTEL WHERE INT_ID=?"
                );
                prep.setInt(1, idDTO);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int id_hotel=0;
                if(res != null) {
                    id_hotel = res.getInt(1);
                }
                return id_hotel;

            }
        });
    }

    @Override
    public Boolean insertHotelManager(final Integer id_hotel, final Integer id_sm, final Integer commission) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO HOTEL_MANAGER(ID_HOTEL,ID_SM,COMMISSION) VALUES (?,?,?)"
                );
                prep.setInt(1, id_hotel);
                prep.setInt(2, id_sm);
                prep.setInt(3, commission);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();

                return true;

            }
        });
    }

    @Override
    public Boolean deleteHotelManager(final Integer id_hotel, final Integer id_sm) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "DELETE FROM HOTEL_MANAGER WHERE ID_HOTEL=? AND ID_SM=?"
                );
                prep.setInt(1, id_hotel);
                prep.setInt(2, id_sm);

                prep.executeQuery();

                return true;
            }
        });
    }

    @Override
    public ArrayList<AccommodationTotalValue> sortAccommodationbyValuable() throws MyException {
        return dbOperation(new WrapperDBOperation<ArrayList<AccommodationTotalValue>>() {

            @Override
            public ArrayList<AccommodationTotalValue> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ACCTYPE,HOTEL.NAME_H,HOTEL.CITY,HOTEL.COUNTRY,TOTALVALUE FROM (" +
                                "          SELECT ACCOMMODATION.id_hotel AS ACCHOTID,ACCOMMODATION.TYPE AS ACCTYPE,ACCOMMODATION.ID_ACC,TOTACC,TOTALVALUE FROM (" +
                                "            SELECT ACCOMMODATION.ID_ACC AS TOTACC, SUM(ORDERS.PRICE) AS TOTALVALUE FROM ORDERS" +
                                "              LEFT JOIN ACCOMMODATION" +
                                "                ON ORDERS.ID_ACC=ACCOMMODATION.ID_ACC" +
                                "            GROUP BY ACCOMMODATION.ID_ACC),ACCOMMODATION" +
                                "          WHERE ACCOMMODATION.ID_ACC=TOTACC" +
                                "          ),HOTEL" +
                                "  WHERE HOTEL.ID_HOTEL=ACCHOTID" +
                                "  ORDER BY TOTALVALUE DESC"
                );

                java.sql.ResultSet results = prep.executeQuery();
                ArrayList<AccommodationTotalValue> accList = new ArrayList<AccommodationTotalValue>();
                while (results.next()) {
                    String type = results.getString(1);
                    String hotel_name = results.getString(2);
                    String city = results.getString(3);
                    String coutry = results.getString(4);
                    double totalValue = results.getDouble(5);

                    AccommodationTotalValue acc = new AccommodationTotalValue(type,hotel_name,city,coutry,totalValue);
                    accList.add(acc);
                }
                return accList;
            }
        });
    }

    @Override
    public List<HotelManagingInfo> getAllOccupyHotelOfSMByID(final Integer idsm) throws MyException {
        return dbOperation(new WrapperDBOperation<List<HotelManagingInfo>>() {
            @Override
            public List<HotelManagingInfo> doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT HOTEL_MANAGER.ID_SM,HOTEL.ID_HOTEL,HOTEL.NAME_H,HOTEL.CITY,HOTEL.COUNTRY, " +
                                "        HOTEL_MANAGER.COMMISSION FROM HOTEL,HOTEL_MANAGER " +
                                "  WHERE ID_SM=? AND HOTEL_MANAGER.ID_HOTEL=HOTEL.ID_HOTEL "
                );
                prep.setInt(1, idsm);
                java.sql.ResultSet results = prep.executeQuery();
                List<HotelManagingInfo> hlist = new ArrayList<HotelManagingInfo>();
                while(results.next()){
                    int idsm = results.getInt(1);
                    int idHotel = results.getInt(2);
                    String hotelName = results.getString(3);
                    String city = results.getString(4);
                    String country = results.getString(5);
                    int comm = results.getInt(6);
                    hlist.add(new HotelManagingInfo(idsm,idHotel,hotelName,city,country,comm));
                }
                return hlist;
            }
        });
    }

    @Override
    public Map<Integer, List<HotelCommissionDTO>> getHotelCommissionsBatch(final List<Integer> hotel_ids) throws MyException {
        return dbOperation(new WrapperDBOperation <Map<Integer, List<HotelCommissionDTO>>>() {

            @Override
            public  Map<Integer, List<HotelCommissionDTO>> doMethod(Connection dataBase) throws MyException, SQLException {
                String ids = "";
                StringBuilder b = new StringBuilder();
                for(Integer id:hotel_ids){
                    b.append(id);
                    b.append(",");
                }
                ids = b.substring(0,b.length()-1);
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT hm.COMMISSION, hm.ID_SM,  h.INT_ID FROM HOTEL h " +
                                "INNER JOIN HOTEL_MANAGER hm ON hm.ID_HOTEL = h.ID_HOTEL" +
                                " WHERE h.INT_ID IN ("+ids+")" +
                                " ORDER BY hm.COMMISSION ASC "
                );

                //prep.setString(1, ids);
                //prep.setString(2, end);
                java.sql.ResultSet results = prep.executeQuery();
                Map<Integer, List<HotelCommissionDTO>> map = new HashMap<Integer, List<HotelCommissionDTO>>();

                while (results.next()) {
                    List<HotelCommissionDTO> accList = new ArrayList<HotelCommissionDTO>();
                    Integer commission = results.getInt(1);
                    Integer smId = results.getInt(2);
                    Integer hotelid = results.getInt(3);
                    if(map.containsKey(hotelid)){
                        accList = map.get(hotelid);
                    }
                    else{
                        map.put(hotelid,accList);
                    }
                    accList.add(new HotelCommissionDTO(smId,commission));
                }
                return map;

            }
        });
    }
}

