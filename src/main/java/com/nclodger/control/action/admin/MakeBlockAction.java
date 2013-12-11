package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 23.11.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class MakeBlockAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {
        // If User is not authorized or not Administrator
        if(request.getSession().getAttribute("utype") == null ||
                !(request.getSession().getAttribute("utype").toString()).equals("3")) {
            return "home";
        }

        UserDao uDao = new UserDao();
        boolean flag = true;
        String[] users = request.getParameterValues("block[]");

        for(int i=0; i<users.length; i++){
            flag = uDao.makeBlock(Integer.parseInt(users[i]));
        }
        return "adsettings";
    }

}
