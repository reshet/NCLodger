package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 10.12.13
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class OpenMyAccountPage extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("email") == null) {
            return "home";
        }
        else {
            return "ussettings";
        }
    }
}
