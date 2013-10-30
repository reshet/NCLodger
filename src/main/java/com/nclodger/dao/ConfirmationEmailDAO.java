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
 * User: pasha
 * Date: 10/30/13
 * Time: 2:51 AM
 * To change this template use File | Settings | File Templates.
 */
//TODO
public class ConfirmationEmailDAO implements ConfirmationEmailDAOInterface {
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
                //return null;
            } catch (SQLException e1) {
                //e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                throw new MyException(e1.getMessage());
            }
        } catch (NamingException e) {
            try {
                dataBase.rollback();
                //return null;
                throw new MyException(e.getMessage());
            } catch (SQLException e1) {
                //e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                throw new MyException(e1.getMessage());
            }
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            try {
                dataBase.close();
            } catch (SQLException e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                //return null;
                throw new MyException(e.getMessage());
            }
        }
    }
    @Override
    public boolean insert(final ConfirmationEmail ConMail) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException,MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO CONFIRM_EMAIL (ID_USER,CONFIRM_HASH,DATE_CONFIRM) values (?,?,?)"
                );
                prep.setInt(1,ConMail.getIdUser());
                prep.setString(2,ConMail.getConfirmHash());
                prep.setDate(3,new java.sql.Date(ConMail.getConfirmDate().getTime())) ;

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }

    @Override
    public boolean update(ConfirmationEmail ConMail) {
        return false;
    }

    @Override
    public boolean delete(ConfirmationEmail ConMail) {
        return false;
    }
}
