package com.nclodger.publicdao;


import com.nclodger.domain.Users;
import com.nclodger.myexception.MyException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface UserDaoInterface {
    public boolean insert(Users user) throws MyException;
    public boolean update (Users user);
    public boolean delete(String email) throws MyException;
    public boolean delete(final int userid) throws MyException;
    public boolean getUser(String email, String password) throws MyException;
    public boolean checkPswd(int userID, String password) throws MyException;
    public List<Users>  getAllUsers() throws MyException;
    public Users find(int id) throws ClassNotFoundException, SQLException, MyException;
    public boolean confirm_register(Users user) throws Exception;
    public boolean confirmRegisterByUserID(int userID) throws Exception;
    public boolean updateForSM(int userID) throws MyException;
    public boolean updateMakeUnvip(int userID) throws MyException;
    public boolean makeBlock(final int userID) throws MyException;
    public boolean makeUnBlock(final int userID) throws MyException;
    public int getUserId(String email) throws MyException;
    public boolean updatePswd(Users u) throws MyException;
    public boolean isExistEmail(String email) throws MyException;
    public String getPasswordbyEmail(String email) throws MyException;
}
