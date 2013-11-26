package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 20.11.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class MakeVIPAction extends Action {

        @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {
            UserDao uDao = new UserDao();
            boolean flag = true; //uDao.updateForSM(521);
            //      request.getParameterValues("vip");
            String[] users = request.getParameterValues("vip[]");

                for(int i=0; i<users.length; i++){
                  flag = uDao.updateForSM(Integer.parseInt(users[i]));
             }
            return "smsettings";
    }

}