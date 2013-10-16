package com.nclodger.dao;

import org.springframework.stereotype.Component;

import javax.enterprise.context.spi.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.cci.ResultSet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
@Component("userdao")
public class UserDao implements UserDaoInterface {
    private static Connection dataBase = null;

    public void insert(int _id, String _email, String _pswd, String _name, int register_confirm) {
        String sql = "INSERT INTO User(id,email,pswd,name,register_confirmed)" +
                "values" +
                "(" + _id + "," + _email + "," + _pswd + "," + _name + ",1);";
    }

    public void confirm_register(Users _user) throws SQLException {
        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "id=" + _user.getId() + ";");
        res.next();
        int _id = res.getInt(1);
        res = st.executeQuery("UPDATE User" +
                "SET confirm_register = 1 " +
                "WHERE id=" + _id + ";");
    }

    @Override
    public void insert(Users _user) {
        //To change body of implemented methods use File | Settings | File Templates.
        int k = 0;
    }

    @Override
    public void update(Users _user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Users _user) throws ClassNotFoundException, SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getUser(String _email, String _password) throws SQLException, NamingException//знайти когось в базі
    {
        // Context ctx = new InitialContext();
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/NCLodger");
        Connection con = ds.getConnection();//тут юзер и пасворд надо будет поменять

        //  Connection con = null;
        Statement st = dataBase.createStatement();
        // TODO Prepared statement read
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "email= " + _email + " AND pswd= " + _password + ";");
        res.next();
        int exist = res.getInt(1);
        boolean answer = false;
        if (exist > 0)
            answer = true;
        return answer;
    }

    @Override
    public Users find(int id) throws ClassNotFoundException, SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
