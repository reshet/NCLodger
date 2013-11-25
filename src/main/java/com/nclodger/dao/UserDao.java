package com.nclodger.dao;

import com.nclodger.myexception.MyException;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
@Component("userdao")
public class UserDao implements UserDaoInterface {

    abstract class WrapperDBOperation<T> {
        abstract public T doMethod(Connection dataBase) throws MyException, SQLException;
    }

    private <T> T booleanOperation(WrapperDBOperation<T> operation) throws MyException {
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
        } finally {
            try {
                dataBase.close();
            } catch (SQLException e) {
                throw new MyException(e.getMessage());
            }
        }
    }

    public boolean insert(String _email, String _pswd, String _name, int register_confirm) throws MyException {
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

    public boolean confirm_register(final Users _user) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws MyException, SQLException {
                //dataBase.
                Statement st = dataBase.createStatement();
                java.sql.ResultSet res = st.executeQuery("SELECT ID_USER FROM Users WHERE " +
                        "ID_USER=" + _user.getId() + ";");
                res.next();
                int _id = res.getInt(1);
                res = st.executeQuery("UPDATE Users" +
                        "SET confirm_register = 1 " +
                        "WHERE ID_USER=" + _id + ";");
                return true;
            }
        });

    }

    public boolean confirmRegisterByUserID(final int userID) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws MyException, SQLException {
                Statement st = dataBase.createStatement();
                PreparedStatement prepGetUserID = dataBase.prepareStatement(
                        "SELECT ID_USER FROM USERS WHERE ID_USER=?"
                );
                prepGetUserID.setInt(1, userID);
                java.sql.ResultSet res = prepGetUserID.executeQuery();
                try {
                    res.next();
                    int id = res.getInt(1);
                    PreparedStatement prepSetUserConfirmStatus = dataBase.prepareStatement(
                            "UPDATE USERS SET CONFIRM_REGISTER =1 WHERE ID_USER=?"
                    );
                    prepSetUserConfirmStatus.setInt(1, userID);
                    java.sql.ResultSet execUpdation = prepSetUserConfirmStatus.executeQuery();
                    execUpdation.next();
                } catch (Exception ex) {
                    throw new MyException(ex.getMessage());
                }
                return true;
            }
        });

    }


    @Override
    public boolean insert(final Users user) throws MyException {
        //Tested valid sql
        //INSERT INTO "Users" (id_user,username,email,pswd,user_type,is_blocked) values (0,'reshet','reshet.ukr@gmail.com','tratata','customer',0);
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "INSERT INTO USERS (USERNAME,EMAIL,PSWD,CONFIRM_REGISTER,ID_UT,IS_BLOCKED) values (?,?,?,0,1,0)"
                );
                prep.setString(1, user.getName());
                prep.setString(2, user.getEmail());
                prep.setString(3, user.getPswd());

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }




    @Override
    public boolean updateForSM(final int userID) throws MyException {
       return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE USERS SET VIP=? WHERE ID_USER=?"
                );

                prep.setInt(1,1);
                prep.setInt(2,userID);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }



    @Override
    public boolean updateMakeUnvip(final int userID) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE USERS SET VIP=? WHERE ID_USER=?"
                );

                prep.setInt(1,0);
                prep.setInt(2,userID);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }


    @Override
    public boolean makeBlock(final int userID) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE USERS SET IS_BLOCKED=? WHERE ID_USER=?"
                );

                prep.setInt(1,1);
                prep.setInt(2,userID);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }



    @Override
    public boolean makeUnBlock(final int userID) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE USERS SET IS_BLOCKED=? WHERE ID_USER=?"
                );

                prep.setInt(1,0);
                prep.setInt(2,userID);

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
    public boolean delete(String email) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getUser(final String email, final String password) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {

            @Override
            public Boolean doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_USER FROM USERS WHERE email=? AND pswd= ?"
                );
                prep.setString(1, email);
                prep.setString(2, password);

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                int exist = res.getInt(1);
                boolean answer = false;
                if (exist > 0) {
                    answer = true;
                }

                return answer;
                //return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }

    @Override
    public boolean checkPswd(int userID, String password) throws MyException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Users> getAllUsers() throws MyException {

        return booleanOperation(new WrapperDBOperation<List<Users>>() {

            @Override
            public List<Users> doMethod(Connection dataBase) throws MyException, SQLException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "SELECT ID_USER,USERNAME,ID_UT,EMAIL,IS_BLOCKED,BONUS,VIP FROM USERS"
                );

                java.sql.ResultSet results = prep.executeQuery();
                List<Users> uList = new ArrayList<Users>();
                while (results.next()) {

                    Integer id = results.getInt(1);
                    String uname = results.getString(2);
                    Integer utype = results.getInt(3);
                    String email = results.getString(4);
                    Integer is_blocked = results.getInt(5);
                    Integer bonus = results.getInt(6);
                    Integer vip = results.getInt(7);


                    Users user = new Users(id, uname);
                    user.setId_ut(utype);
                    user.setEmail(email);
                    user.setIs_blocked(is_blocked);
                    user.setBonus(bonus);
                    user.setVip(vip);

                    uList.add(user);
                }
                return uList;
                }
        });
    }



    public Users getUserObj(final String email, final String password) throws MyException {
                return booleanOperation(new WrapperDBOperation<Users>() {

                    @Override
                    public Users doMethod(Connection dataBase) throws MyException, SQLException {
                        PreparedStatement prep = dataBase.prepareStatement(
                                "SELECT ID_USER,USERNAME,ID_UT,EMAIL, CONFIRM_REGISTER FROM USERS WHERE email=? AND pswd= ?"
                        );
                        prep.setString(1, email);
                        prep.setString(2, password);

                        java.sql.ResultSet res = prep.executeQuery();
                        res.next();
                        int id = res.getInt(1);
                        boolean answer = false;
                        if (id > 0) {
                            //answer = true;
                            String uname = res.getString(2);
                            Integer utype = res.getInt(3);
                            String email = res.getString(4);
                            Integer confirmed = res.getInt(5);
                            //String pswd = res.getString(5);
                            Users user = new Users(id, uname);
                            user.setId_ut(utype);

                            user.setEmail(email);
                            user.set_confirm_register(confirmed);
                            //user.setPswd();
                            return user;
                        }

                        return null;
                        //return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

            }

            @Override
            public Users find(final int id) throws MyException {
                //return null;  //To change body of implemented methods use File | Settings | File Templates.
                return booleanOperation(new WrapperDBOperation<Users>() {

                    @Override
                    public Users doMethod(Connection dataBase) throws MyException, SQLException {
                        PreparedStatement prep = dataBase.prepareStatement(
                                "SELECT * FROM Users WHERE id=?"
                        );
                        prep.setInt(1, id);
                        //prep.setString(2,password);

                        java.sql.ResultSet res = prep.executeQuery();
                        res.next();
                        int exist = res.getInt(1);
                        Users user = new Users(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5));
                        return user;
                        //return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
            }

        @Override
        public int getUserId(final String email) throws MyException {
            /**
             * Working sql query example:
             * SELECT ID_SM FROM MANAGER WHERE MANAGER.ID_USER IN
             *      (SELECT ID_USER FROM USERS WHERE USERS.EMAIL = 'reshet.ukr@gmail.com')
             */
            return booleanOperation(new WrapperDBOperation<Integer>() {
                @Override
                public Integer doMethod(Connection dataBase) throws SQLException, MyException {
                    PreparedStatement prep = dataBase.prepareStatement(
                            "SELECT ID_USER FROM USERS WHERE USERS.EMAIL=?"
                    );
                    prep.setString(1,email);
                    java.sql.ResultSet res = prep.executeQuery();
                    res.next();
                    int idSm = res.getInt(1);
                    return idSm;
                }
            });
        }

    @Override
    public boolean updatePswd(final Users u) throws MyException {
        return booleanOperation(new WrapperDBOperation<Boolean>() {
            @Override
            public Boolean doMethod(Connection dataBase) throws SQLException, MyException {
                PreparedStatement prep = dataBase.prepareStatement(
                        "UPDATE USERS SET PSWD=? WHERE ID_USER=?"
                );

                prep.setString(1, u.getPswd());
                prep.setInt(2,u.getId());

                java.sql.ResultSet res = prep.executeQuery();
                res.next();
                return true;
            }
        });
    }


}
