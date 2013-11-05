package com.nclodger.control.action;

import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.mail.MailConfirmation;
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
public class ConfirmationAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String hash = request.getParameter("confirmation");

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("dao-bean-config.xml");
            ConfirmationEmailDAO ConMail =(ConfirmationEmailDAO) context.getBean("conemailDAO");
            int userID = ConMail.getUserIDbyHash(hash);
            //Change user confirm status to 1
            UserDao UserDAO = (UserDao) context.getBean("userDAO");
            UserDAO.confirmRegisterByUserID(userID);
            ConMail.deleteByHash(hash);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "confirmation";
    }
}
