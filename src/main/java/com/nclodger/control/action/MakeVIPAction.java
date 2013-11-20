package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 20.11.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class MakeVIPAction implements Action {

    private void makeVip(HttpServletRequest request, HttpServletResponse response) throws MyException {
        UserDao uDao = new UserDao();
        boolean flag = uDao.updateForSM(63);
        request.getSession().setAttribute("user_field", "VIP");


    }

        @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
            makeVip(request, response);
           return "smsettings";
    }

}