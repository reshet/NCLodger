package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 10.12.13
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class OpenMyAccountPage extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object emailObject = request.getSession().getAttribute("email");
        if(emailObject == null) {
            return "home";
        }
        else {
            UserDao userDao = new UserDao();
            Double bonus = userDao.getBonusBalance(emailObject.toString());
            request.getSession().setAttribute("bonus",bonus);
            return "ussettings";
        }
    }
}
