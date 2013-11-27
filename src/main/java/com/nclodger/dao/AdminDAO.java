package com.nclodger.dao;

import com.nclodger.domain.SManager;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.AdminDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 23.11.13
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class AdminDAO extends AbstractRepository implements AdminDAOInterface {

    @Override
    public boolean update(final double commission, final double vip_disc, final double user_disc) throws MyException {
        return dbOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE INITIAL_DISCOUNT SET COMMISSION=?,VIP_DISCOUNT=?,USER_DISCOUNT=?  WHERE ID_ID=1"
                );

                prep.setDouble(1, commission);
                prep.setDouble(2, vip_disc);
                prep.setDouble(3, user_disc);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }

    @Override
    public SManager getCurDefaultCommAndDisc() throws MyException {
        return dbOperation(new WrapperDBOperation<SManager>() {
            @Override
            public SManager doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT COMMISSION,VIP_DISCOUNT,USER_DISCOUNT FROM INITIAL_DISCOUNT WHERE ID_ID=1"
                );
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                SManager sm = new SManager(res.getDouble(1),res.getDouble(2),res.getDouble(3));
                return sm;
            }
        });
    }
}
