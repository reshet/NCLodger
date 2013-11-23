package com.nclodger.dao;

import com.nclodger.myexception.MyException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 23.11.13
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class AdminDAO implements AdminInteface {

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
    public boolean update(final double commission, final double vip_disc, final double user_disc) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE INITIAL_DISCOUNT SET COMMISSION=?,VIP_DISCOUNT=?,USER_DISCOUNT=?  WHERE ID_ID=1"
                );

                prep.setDouble(1,commission);
                prep.setDouble(2,vip_disc);
                prep.setDouble(3,user_disc);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }
}
