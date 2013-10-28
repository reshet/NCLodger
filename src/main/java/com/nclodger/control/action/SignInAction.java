package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignInAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        UserDao users = new UserDao();
        boolean bool;
        try {
            bool = users.getUser(request.getParameter("email"),request.getParameter("password"));
        } catch (MyException ex) {
            return "exception";
        }
        return "home";
    }
}
