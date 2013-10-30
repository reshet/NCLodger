package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 30.10.13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class SmSettingsAction implements Action  {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "smsettings";
    }
}
