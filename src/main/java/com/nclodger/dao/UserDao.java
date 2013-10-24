package com.nclodger.dao;

import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
    /*static{


    }*/
    abstract class WrapperDBOperation<T> {
        abstract public T doMethod(Connection dataBase) throws SQLException;

    }
    private <T> T booleanOperation (WrapperDBOperation<T> operation){
        Connection dataBase = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/NCLodger");
            dataBase = ds.getConnection();
            return operation.doMethod(dataBase);
        } catch (SQLException e) {
            try {
                dataBase.rollback();
                return null;
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NamingException e) {
            try {
                dataBase.rollback();
                return null;
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            try {
                dataBase.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return null;
            }

        }
        return null;
    }

    public boolean insert(int _id, String _email, String _pswd, String _name, int register_confirm) {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) {
                return true;
                /*String sql = "INSERT INTO User(id,email,pswd,name,register_confirmed)" +
                        "values" +
                        "(" + _id + "," + _email + "," + _pswd + "," + _name + ",1);";*/
            }
        });

    }

    public boolean confirm_register(final Users _user) throws SQLException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException {
                Statement st = dataBase.createStatement();
                java.sql.ResultSet res = st.executeQuery("SELECT id FROM Users WHERE " +
                        "id=" + _user.getId() + ";");
                res.next();
                int _id = res.getInt(1);
                res = st.executeQuery("UPDATE User" +
                        "SET confirm_register = 1 " +
                        "WHERE id=" + _id + ";");
                return true;
                /*String sql = "INSERT INTO User(id,email,pswd,name,register_confirmed)" +
                        "values" +
                        "(" + _id + "," + _email + "," + _pswd + "," + _name + ",1);";*/
            }
        });

    }

    @Override
    public boolean insert(final Users user) {
        //Tested valid sql
        //INSERT INTO "Users" (id_user,username,email,pswd,user_type,is_blocked) values (0,'reshet','reshet.ukr@gmail.com','tratata','customer',0);
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO Users (id_user, username,email,pswd,is_blocked) values (?,?,?,?,0);"
                );
                //NamedParameterStatement p = new NamedParameterStatement(con, query);
                prep.setInt(1,10);
                prep.setString(2,user.getName());
                prep.setString(3,user.getEmail());
                prep.setString(4,user.getPswd());
                //prep.setInt(5,0);
                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }

    @Override
    public boolean update(Users _user) {
        //To change body of implemented methods use File | Settings | File Templates.
        //TODO method
        return false;
    }

    @Override
    public boolean delete(Users _user) throws ClassNotFoundException, SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        //TODO method
        return false;

    }

    public boolean getUser(final String email, final String password) throws SQLException, NamingException//знайти когось в базі
    {
        return booleanOperation(new WrapperDBOperation<Boolean>() {

            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT id FROM User WHERE email=? AND pswd= ?"
                );
                prep.setString(1,email);
                prep.setString(2,password);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int exist = res.getInt(1);
                boolean answer = false;
                if (exist > 0){
                    answer = true;
                }

                return answer;
                //return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    @Override
    public Users find(final int id) throws ClassNotFoundException, SQLException {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        return booleanOperation(new WrapperDBOperation<Users>() {

            @Override
            public Users doMethod(Connection dataBase) throws SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT * FROM User WHERE id=?"
                );
                prep.setInt(1, id);
                //prep.setString(2,password);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int exist = res.getInt(1);
                Users user = new Users(res.getInt(1),res.getString(2), res.getString(3),res.getString(4),res.getInt(5));
                return user;
                //return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
