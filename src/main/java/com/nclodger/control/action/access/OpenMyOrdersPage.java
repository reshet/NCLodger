package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 09.12.13
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class OpenMyOrdersPage extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("email") == null) {
            return "home";
        }
        else {
            return "myorders";
        }
    }
}
