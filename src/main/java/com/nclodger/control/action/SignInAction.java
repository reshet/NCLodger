package com.nclodger.control.action;

import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.myexception.MyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        UserDao users = (UserDao) context.getBean("userDAO");
        Users user;
        try {
            user = users.getUserObj(request.getParameter("email"),request.getParameter("password"));
            if(user!=null && user.get_confirm_register()==1){
                request.getSession().setAttribute("username",user.getName());
                request.getSession().setAttribute("utype",user.getId_ut());
            }
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "home";
    }
}
