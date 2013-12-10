package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 09.12.13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class OpenLoginPageAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("email") == null) {
            return "login";
        }
        else {
            return "home";
        }
    }
}
