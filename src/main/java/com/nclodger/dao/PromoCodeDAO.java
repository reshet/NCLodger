package com.nclodger.dao;

import com.nclodger.domain.PromoCode;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.PromoCodeDAOInterface;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("promocodeDAO")
public class PromoCodeDAO extends AbstractRepository implements PromoCodeDAOInterface {

    @Override
    public boolean insert(final PromoCode pc) throws MyException {
        /**
         * Working sql query example:
         * INSERT INTO PROMOCODE(CODE, START_DATE,END_DATE,discount,ISUSED,ID_SM)
         * VALUES('UKNSGC-1', TO_DATE('10/11/2013', 'MM-DD-YYYY'), TO_DATE('12/11/2013', 'MM-DD-YYYY'),0.1, 0, 1)
         */
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO PROMOCODE (code,start_date,end_date,discount,isUsed, id_sm) values (?,?,?,?,?,?)"
                );
                try {
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date date1 = formatter.parse(pc.getStart_date());
                    Date date2 = formatter.parse(pc.getEnd_date());
                    java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
                    java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());

                    prep.setString(1, pc.getCode());
                    prep.setDate(2, sqlDate1);
                    prep.setDate(3, sqlDate2);
                    prep.setDouble(4, pc.getDiscount());
                    prep.setInt(5, pc.getStatus());
                    prep.setInt(6, pc.getId_sm());

                    java.sql.ResultSet res = prep.executeQuery();
                    res.next();

                } catch (Exception ex) {
                    throw new MyException(ex.getMessage());
                }
                return true;
            }
        });
    }

    @Override
    public List<PromoCode> getAllPCbySMid(final int id_sm) throws MyException {
        return dbOperation(new WrapperDBOperation<List<PromoCode>>() {

            @Override
            public List<PromoCode> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_PC,CODE,START_DATE,END_DATE,DISCOUNT,ISUSED FROM PROMOCODE WHERE ID_SM=?"
                );
                prep.setInt(1, id_sm);
                java.sql.ResultSet results = prep.executeQuery();
                List<PromoCode> pcList = new ArrayList<PromoCode>();
                while (results.next()) {
                    int id = results.getInt(1);
                    String code = results.getString(2);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String start_date = df.format(results.getDate(3));
                    String end_date = df.format(results.getDate(4));
                    double discount = results.getDouble(5);
                    int status = results.getInt(6);
                    PromoCode pc = new PromoCode(id, code, start_date, end_date, discount, status);
                    pcList.add(pc);
                }
                return pcList;
            }
        });
    }

    //return true if promocode exist
    @Override
    public boolean isExist(final String code) throws MyException {

        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(1) FROM PROMOCODE WHERE CODE=?"
                );

                prep.setString(1, code);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int check = res.getInt(1);
                boolean answer = false;
                if (check != 0) {
                    answer = true;
                }
                return answer;

            }
        });
    }



    //return true if code was used
    @Override
    public boolean isUsed(final String code) throws MyException {

        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ISUSED FROM PROMOCODE WHERE CODE=?"
                );

                prep.setString(1, code);
                java.sql.ResultSet res = prep.executeQuery();
                boolean answer = false;
                if (res.next()) {
                    int check = res.getInt(1);
                    if (check == 1) {
                        answer = true;
                    }
                }
                return answer;

            }
        });
    }

    //true if promocode time is expired
    @Override
    public boolean isExpired(final String code) throws MyException {

        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COUNT(1) FROM PROMOCODE WHERE CODE=? AND ISUSED=2"
                );

                prep.setString(1, code);


                java.sql.ResultSet res = prep.executeQuery();
                boolean answer = false;
                res.next();
                int check = res.getInt(1);
                if (check != 0) {
                    answer = true;
                }

                return answer;

            }
        });
    }

    @Override
    public PromoCode get(final String code) throws MyException {
        return dbOperation(new WrapperDBOperation<PromoCode>() {
            @Override
            public PromoCode doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_PC,CODE,START_DATE,END_DATE,DISCOUNT,ISUSED FROM PROMOCODE WHERE CODE=?"
                );

                prep.setString(1, code);
                java.sql.ResultSet results = prep.executeQuery();
                results.next();
                int id = results.getInt(1);
                if(id == 0) return null;
                String code = results.getString(2);
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String start_date = df.format(results.getDate(3));
                String end_date = df.format(results.getDate(4));
                double discount = results.getDouble(5);
                int status = results.getInt(6);
                PromoCode pc = new PromoCode(id, code, start_date, end_date, discount, status);

                return pc;

            }
        });
    }



}
