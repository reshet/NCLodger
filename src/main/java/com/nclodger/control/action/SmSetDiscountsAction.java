package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav Dmytruk
 * Date: 21.11.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class SmSetDiscountsAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "smsettings";
    }
}
