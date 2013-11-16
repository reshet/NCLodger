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
    public boolean insert(final ConfirmationEmail ConMail) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException,MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO CONFIRM(ID_USER,CONFIRM_HASH,DATE_C) values (?,?,?)"
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
    public boolean deleteByHash(final String hash) throws MyException{
        return booleanOperation(new WrapperDBOperation<Boolean>() {

            @Override
            public Boolean doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "DELETE FROM CONFIRM WHERE CONFIRM_HASH=?"
                );
                prep.setString(1, hash);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }

    @Override
    public int getUserIDbyHash(final String hash) throws MyException{
        return booleanOperation(new WrapperDBOperation<Integer>()  {

            @Override
            public Integer doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_USER FROM CONFIRM WHERE CONFIRM_HASH=?"
                );
                prep.setString(1, hash);

                java.sql.ResultSet res = prep.executeQuery();

                //?if there are no hash, return NULL
                try{
                res.next();
                }
                catch (Exception ex){
                    throw new MyException(ex.getMessage());
                }
                int userID = res.getInt(1);
                return userID;
            }

        });
    }
}

