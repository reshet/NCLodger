package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 23.11.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class AdminGetAllUsersAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        UserDao userDAO = (UserDao) context.getBean("userDAO");
        List<Users> users = userDAO.getAllUsers();
        request.setAttribute("allusers",users);

        return "adsettings";
    }
}