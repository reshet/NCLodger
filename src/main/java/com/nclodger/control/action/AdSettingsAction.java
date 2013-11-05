package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 05.11.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class AdSettingsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("utype")!=null && (Integer)request.getSession().getAttribute("utype") == 3) {
            return "adsettings";
        }
        else {
            return "home";
        }
    }
}
