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
public class SignInAction implements Action {
    UserDao users;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        users = new UserDao();
        Users user;
        try {
            user = users.getUserObj(request.getParameter("email"),request.getParameter("password"));
            if(user!=null){
                request.getSession().setAttribute("username",user.getName());
            }
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "home";
    }
}
