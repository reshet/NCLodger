package com.nclodger.dao;

import javax.activation.DataSource;
import javax.enterprise.context.spi.Context;
import javax.jms.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.cci.ResultSet;
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
public abstract class UserDao implements UserDaoInterface
{
    private static Connection database = null;

    public void insert(int _id, String _email, String _pswd, String _name, int register_confirm)
    {
        String sql = "INSERT INTO User(id,email,pswd,name,register_confirmed " +
                "values" +
                "("+_id+","+_email+","+_pswd+","+_name+",1);";
    }


    public void confirm_register(User _user) throws SQLException
    {
        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "id="+_user.getId()+";");
        res.next();
        int _id = res.getInt(1);
        res = st.executeQuery("UPDATE User" +
                "SET confirm_register = 1 " +
                "WHERE id="+_id+";");
    }



    public boolean getUser(String _email, String _password) throws SQLException, NamingException//знайти когось в базі
    {
       // Context ctx = new InitialContext();
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("jdbc/NCLodger");
        Connection con = ds.getConnection(username, password);//тут юзер и пасворд надо будет поменять

      //  Connection con = null;
        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "email= " + _email + " AND pswd= " + _password + ";");
        res.next();
        int exist = res.getInt(1);
        boolean answer = false;
        if(exist>0)
            answer = true;
        return answer;
    }
}
