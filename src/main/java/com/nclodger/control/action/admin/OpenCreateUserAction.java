package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/12/13
 * Time: 3:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class OpenCreateUserAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "createuser";
    }
}
