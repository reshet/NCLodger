package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface UserDaoInterface
{
    void insert(User _user);
    void update (User _user);
    User getUser(String _email, String _password);
    ArrayList<User> findByCriteria(String _name, String _password);
    void confirm_register(User _user);
}
