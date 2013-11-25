package com.nclodger.control.action;

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        UserDao users = (UserDao) context.getBean("userDAO");
        if(!users.isExistEmail(request.getParameter("email"))){
            request.setAttribute("regConfirm",true);
            return "login";
        }
        else if(users.isExistEmail(request.getParameter("email")) && !users.getUser(request.getParameter("email"),request.getParameter("password")))
        {
            request.setAttribute("wrongPass",true);
            return "login";
        }


        Users user;
        try {
            user = users.getUserObj(request.getParameter("email"),request.getParameter("password"));
            if (user != null && user.get_confirm_register() == 1){
                request.getSession().setAttribute("email",user.getEmail());
                request.getSession().setAttribute("username",user.getName());
                request.getSession().setAttribute("utype",user.getId_ut());
                request.getSession().setAttribute("userfull",user);
            }
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "home";
    }
}
