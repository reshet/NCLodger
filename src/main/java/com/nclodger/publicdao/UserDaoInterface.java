package com.nclodger.publicdao;


import com.nclodger.additional.BookingViewing;
import com.nclodger.domain.Order;
import com.nclodger.domain.User;
import com.nclodger.myexception.MyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
/*@Component("userDAO")*/
public interface UserDaoInterface {
    public boolean insert(User user) throws MyException;
    public boolean update (User user) throws MyException;
    public boolean delete(String email) throws MyException;
    public boolean delete(final int userid) throws MyException;
    public boolean getUser(String email, String password) throws MyException;
    public boolean checkPswd(int userID, String password) throws MyException;
    public List<User>  getAllUsers() throws MyException;
    public User find(int id) throws  MyException;
    public boolean confirm_register(User user) throws MyException;
    public boolean confirmRegisterByUserID(int userID) throws MyException;
    public boolean updateForSM(int userID) throws MyException;
    public boolean updateMakeUnvip(int userID) throws MyException;
    public boolean makeBlock(final int userID) throws MyException;
    public boolean makeUnBlock(final int userID) throws MyException;
    public int getUserId(String email) throws MyException;
    public boolean updatePswd(User u) throws MyException;
    public boolean isExistEmail(String email) throws MyException;
    public String getPasswordbyEmail(String email) throws MyException;
    public ArrayList<BookingViewing> getPastOrder(int userID) throws MyException;
    public boolean saveOrder(Order ord) throws MyException;
    public Double getBonusBalance(String email) throws MyException;
    public Boolean updateBonusBalance(double newBonus, String email) throws MyException;

}
