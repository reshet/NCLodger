package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 24.11.13
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class GrantToSMAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If User is not authorized or not Administrator
        if(request.getSession().getAttribute("utype") == null ||
                !(request.getSession().getAttribute("utype").toString()).equals("3")) {
            return "home";
        }

        SMDAO smDao = new SMDAO();
        boolean flag = true;
        String[] users = request.getParameterValues("block[]");

        for(int i=0; i<users.length; i++){
            flag = smDao.insert(Integer.parseInt(users[i]));
        }

        return "adsettings";
    }
}
