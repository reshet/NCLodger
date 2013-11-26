package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 25.11.13
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class DismissSMAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SMDAO smDao = new SMDAO();
        boolean flag = true;
        String[] users = request.getParameterValues("block[]");

        for(int i=0; i<users.length; i++){
            flag = smDao.delete(Integer.parseInt(users[i]));
        }

        return "adsettings";
    }
}