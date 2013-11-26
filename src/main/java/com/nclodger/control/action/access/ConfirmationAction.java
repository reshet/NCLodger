package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;
import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/30/13
 * Time: 5:48 PM
 */
public class ConfirmationAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String hash = request.getParameter("param");

        try {
            //ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
            ConfirmationEmailDAO ConMail =(ConfirmationEmailDAO) ctx.getBean("conemailDAO");
            int userID = ConMail.getUserIDbyHash(hash);
            //Change user confirm status to 1
            UserDao UserDAO = (UserDao) ctx.getBean("userDAO");
            UserDAO.confirmRegisterByUserID(userID);
            ConMail.deleteByHash(hash);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "confirmation";
    }
}
