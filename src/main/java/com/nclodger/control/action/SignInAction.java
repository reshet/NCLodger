package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;
import org.springframework.beans.factory.annotation.Autowire;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignInAction implements Action {
    //@Inject
    UserDao users;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        //!!!! Are you spring user?
        users = new UserDao();


        boolean bool;
        try {
            bool = users.getUser(request.getParameter("email"),request.getParameter("password"));
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "home";
    }
}
