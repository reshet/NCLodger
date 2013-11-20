package com.nclodger.dao;

import com.nclodger.myexception.MyException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PromoCodeDAO implements PromoCodeDAOInterface {

    abstract class WrapperDBOperation<T> {
        abstract public T doMethod(Connection dataBase) throws MyException, SQLException;
    }

    private <T> T booleanOperation (WrapperDBOperation<T> operation) throws MyException {
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
        }finally {
            try {
                dataBase.close();
            } catch (SQLException e) {

                throw new MyException(e.getMessage());
            }
        }
    }

    @Override
    public boolean insert(final PromoCode pc) throws MyException {
        /**
         * Working sql query example:
         * INSERT INTO PROMOCODE(CODE, START_DATE,END_DATE,discount,ISUSED,ID_SM)
         * VALUES('UKNSGC-1', TO_DATE('10/11/2013', 'MM-DD-YYYY'), TO_DATE('12/11/2013', 'MM-DD-YYYY'),0.1, 0, 1)
         */
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO PROMOCODE (code,start_date,end_date,discount,isUsed, id_sm) values (?,?,?,?,?,?)"
                );
                try {
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date date1 = formatter.parse(pc.getStart_date());
                    Date date2 = formatter.parse(pc.getEnd_date());
                    java.util.Date utilDate1 = new java.util.Date();
                    java.util.Date utilDate2 = new java.util.Date();
                    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
                    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());

                    prep.setString(1,pc.getCode());
                    prep.setDate(2, sqlDate1);
                    prep.setDate(3, sqlDate2);
                    prep.setDouble(4, pc.getDiscount());
                    prep.setInt(5,pc.getUsed());
                    prep.setInt(6,pc.getId_sm());

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
        return booleanOperation(new WrapperDBOperation<List<PromoCode>>() {

            @Override
            public List<PromoCode> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT id_pc,code,start_date,end_date,discount,isUsed FROM PROMOCODE WHERE is_sm=?"
                );

                prep.setInt(1,id_sm);
                java.sql.ResultSet results = prep.executeQuery();
                List<PromoCode> pcList = new ArrayList<PromoCode>();
                while (results.next()) {

                    int id = results.getInt(1);
                    String code = results.getString(2);
                    Date start_date = results.getDate(3);
                    Date end_date = results.getDate(4);
                    int discount = results.getInt(5);
                    int isUsed = results.getInt(6);

//                    PromoCode pc = new PromoCode(id,code,start_date,end_date,discount,isUsed);
//                    pcList.add(pc);
                }
                return pcList;
            }
        });
    }
}
