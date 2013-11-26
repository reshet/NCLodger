package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 26.11.13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class AdminDeleteUserAction  extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();
        boolean flag = true;
        String[] users = request.getParameterValues("block[]");

        for(int i=0; i<users.length; i++){
            flag = userDao.delete(Integer.parseInt(users[i]));
        }


        return "adsettings";
    }
}
