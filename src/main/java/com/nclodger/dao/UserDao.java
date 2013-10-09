package com.nclodger.dao;

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

        String sql = "INSERT INTO Users(id,email,pswd,name,register_confirmed) " +
                "values" +
                "("+_id+",'"+_email+"','"+_pswd+"','"+_name+"',1);";
        Statement st = dataBase.createStatement();
        st.execute(sql);
    }


    public void confirm_register(Users _user) throws Exception
    {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1158:v$instance", "SYSTEM", "1521");
        dataBase = DriverManager.getConnection(jdbcUrl);

        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM Users WHERE " +
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
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM Users WHERE " +
                "email= " + _email + " AND pswd= " + _password + ";");
        res.next();
        int exist = res.getInt(1);
        boolean answer = false;
        if(exist>0)
            answer = true;
        return answer;
    }


    public Users find(int id) throws ClassNotFoundException, SQLException
    {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1158:v$instance", "SYSTEM", "1521");
        dataBase = DriverManager.getConnection(jdbcUrl);

        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT * FROM Users " +
                "WHERE id= " + id + ";");

        Users user = null;
        while(res.next())
        {
            user = new Users(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5));
        }

        return user;

    }
}
