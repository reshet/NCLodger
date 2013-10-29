package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.myexception.MyException;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignUpAction implements Action
{
//@Bean("userdao")
UserDao users;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        users = new UserDao();
        boolean bool;
        try {
            Users user = new Users(1,request.getParameter("email"),request.getParameter("password1"),request.getParameter("username"),0);
            bool = users.insert(user);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "signup_succeed";
    }
}
