package com.nclodger.dao;

import org.springframework.stereotype.Component;

import javax.enterprise.context.spi.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.cci.ResultSet;
import javax.sql.DataSource;
import java.sql.*;

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
    static{

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/NCLodger");
            dataBase = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public boolean insert(Users user) {
        //Tested valid sql
        //INSERT INTO "Users" (id_user,username,email,pswd,user_type,is_blocked) values (0,'reshet','reshet.ukr@gmail.com','tratata','customer',0);
       Connection dbConnection = null;
       PreparedStatement preparedStatement = null;

       try {
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:NCLodger","user","password");
            PreparedStatement prep = dbConnection.prepareStatement(
                    "INSERT INTO Users (id_user, username,email,pswd,is_blocked) values (?,?,?,?,0);"
            );
            //NamedParameterStatement p = new NamedParameterStatement(con, query);
            prep.setInt(1,10);
            prep.setString(2,user.getName());
            prep.setString(3,user.getEmail());
            prep.setString(4,user.getPswd());
            prep.setInt(5,0);
            prep.addBatch();
            prep.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;
    }

    @Override
    public boolean update(Users _user) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(Users _user) throws ClassNotFoundException, SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getUser(String _email, String _password) throws SQLException, NamingException, ClassNotFoundException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Users find(int id) throws ClassNotFoundException, SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean confirmRegister(Users _user) throws Exception {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }



  /*
    public boolean insert(int _id, String _email, String _pswd, String _name, int register_confirm) {

      //  String url = "jdbc:oracle:thin:@localhost:1521:ORCL";

        String sql = "INSERT INTO User(id,email,pswd,name,registerConfirmed)" +
                "values" +
                "(" + _id + "," + _email + "," + _pswd + "," + _name + ",1);";
        //transactions
        return true;
    }

    public boolean confirmRegister(Users _user) throws SQLException {
        Statement st = dataBase.createStatement();
        java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "id=" + _user.getId() + ";");
        res.next();
        int _id = res.getInt(1);
        res = st.executeQuery("UPDATE User" +
                "SET confirmRegister = 1 " +
                "WHERE id=" + _id + ";");
        return true;
    }

    @Override
    public boolean update(Users _user) {
        //To change body of implemented methods use File | Settings | File Templates.
        return true;
    }

    @Override
    public boolean delete(Users _user) throws ClassNotFoundException, SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        return true;
    }

    public boolean getUser(String _email, String _password) throws SQLException, NamingException//знайти когось в базі
    {
        // Context ctx = new InitialContext();
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/NCLodger");
        Connection con = ds.getConnection();

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

    */
}
