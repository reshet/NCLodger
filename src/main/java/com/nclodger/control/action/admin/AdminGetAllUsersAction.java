package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.User;

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
public class AdminGetAllUsersAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        UserDao userDAO = (UserDao) ctx.getBean("userDAO");
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("allusers",users);

        return "adsettings";
    }
}