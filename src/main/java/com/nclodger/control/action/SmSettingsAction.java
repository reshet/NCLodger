package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 30.10.13
 * Time: 12:36
 */
public class SmSettingsAction implements Action  {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //if((Integer)request.getSession().getAttribute("utype") == 2)
            return "smsettings";
        //else
            //return "home";
    }
}
