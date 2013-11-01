package com.nclodger.control.action;

import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;

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
            ConfirmationEmailDAO ConMail = new ConfirmationEmailDAO();
            int userID = ConMail.getUserIDbyHash(hash);
            //Change user confirm status to 1
            new UserDao().confirmRegisterByUserID(userID);
            ConMail.deleteByHash(hash);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "confirmation";
    }
}
