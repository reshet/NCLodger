package com.nclodger.control.action;

import com.nclodger.dao.SMDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 25.11.13
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class DismissSMAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SMDao smDao = new SMDao();
        boolean flag = true;
        String[] users = request.getParameterValues("block[]");

        for(int i=0; i<users.length; i++){
            flag = smDao.delete(Integer.parseInt(users[i]));
        }

        return "adsettings";
    }
}
