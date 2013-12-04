package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.User;
import com.nclodger.myexception.MyException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignInAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        UserDao users = (UserDao) ctx.getBean("userDAO");
        if(!users.isExistEmail(request.getParameter("email"))){
            request.setAttribute("regConfirm",true);
            return "login";
        }
        else if(users.isExistEmail(request.getParameter("email")) && !users.getUser(request.getParameter("email"),request.getParameter("password")))
        {
            request.setAttribute("wrongPass",true);
            return "login";
        }


        User user;
        try {
            user = users.getUserObj(request.getParameter("email"),request.getParameter("password"));
            //check for confirmation to login
            if(user.get_confirm_register()!=1){
                request.setAttribute("regConfirm",true);
                return "login";
            }
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
