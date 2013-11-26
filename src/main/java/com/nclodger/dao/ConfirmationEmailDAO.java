package com.nclodger.dao;

import com.nclodger.domain.ConfirmationEmail;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.ConfirmationEmailDAOInterface;
import org.springframework.stereotype.Component;

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
@Component("conemailDAO")
public class ConfirmationEmailDAO extends AbstractRepository implements ConfirmationEmailDAOInterface {

    @Override
    public boolean insert(final ConfirmationEmail ConMail) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO CONFIRM(ID_USER,CONFIRM_HASH,DATE_C) values (?,?,?)"
                );
                prep.setInt(1, ConMail.getIdUser());
                prep.setString(2, ConMail.getConfirmHash());
                prep.setDate(3, new java.sql.Date(ConMail.getConfirmDate().getTime()));

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
        return dbOperation(new WrapperDBOperation<Boolean>() {

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
        return dbOperation(new WrapperDBOperation<Integer>() {

            @Override
            public Integer doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_USER FROM CONFIRM WHERE CONFIRM_HASH=?"
                );
                prep.setString(1, hash);

                java.sql.ResultSet res = prep.executeQuery();

                //?if there are no hash, return NULL
                try {
                    res.next();
                } catch (Exception ex) {
                    throw new MyException(ex.getMessage());
                }
                int userID = res.getInt(1);
                return userID;
            }

        });
    }
}

