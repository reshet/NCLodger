package com.nclodger.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.resource.cci.ResultSet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserDao implements UserDaoInterface
{
   //public static Connection database = null;
    private Connection dataBase = null;

    public void insert(int _id, String _email, String _pswd, String _name, int register_confirm) throws ClassNotFoundException, SQLException
    {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1158:v$instance", "SYSTEM", "1521");
        dataBase = DriverManager.getConnection(jdbcUrl);

        String sql = "INSERT INTO User(id,email,pswd,name,register_confirmed) " +
                "values" +
                "("+_id+",'"+_email+"','"+_pswd+"','"+_name+"',1);";
        Statement st = dataBase.createStatement();
        st.execute(sql);
    }


    public void confirm_register(User _user) throws SQLException, ClassNotFoundException {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1158:v$instance", "SYSTEM", "1521");
        dataBase = DriverManager.getConnection(jdbcUrl);

        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "id="+_user.getId()+";");
        res.next();
        int _id = res.getInt(1);
        res = st.executeQuery("UPDATE User" +
                "SET confirm_register = 1 " +
                "WHERE id="+_id+";");
    }



    public boolean getUser(String _email, String _password) throws SQLException, ClassNotFoundException
    {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1158:v$instance", "SYSTEM", "1521");
        dataBase = DriverManager.getConnection(jdbcUrl);

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
