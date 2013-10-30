package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignOutAction implements Action {
    //@Inject
    //UserDao users;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {

            request.getSession().removeAttribute("username");


        return "home";
    }
}