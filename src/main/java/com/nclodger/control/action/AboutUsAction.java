package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Ferbolg
 * Date: 09.12.13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class AboutUsAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        return "aboutus";
    }
}
