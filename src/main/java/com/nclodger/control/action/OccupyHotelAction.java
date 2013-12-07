package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 07.12.13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class OccupyHotelAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "smsettings";
    }
}
