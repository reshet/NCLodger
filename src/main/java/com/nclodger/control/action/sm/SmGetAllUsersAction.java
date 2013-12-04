package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 11/6/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmGetAllUsersAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            //ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
            UserDao userDAO = (UserDao) ctx.getBean("userDAO");
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("allusers",users);

        return "smsettings";
    }
}
