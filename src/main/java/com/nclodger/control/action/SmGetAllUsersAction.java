package com.nclodger.control.action;

import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.webservices.Hotel;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
public class SmGetAllUsersAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
            UserDao userDAO = (UserDao) context.getBean("userDAO");
            List<Users> users = userDAO.getAllUsers();
            request.setAttribute("allusers",users);

        return "smsettings";
    }
}
