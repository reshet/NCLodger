package com.nclodger.control.action;

import com.nclodger.dao.AdminDAO;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 23.11.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class AdInitialDiscountsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminDAO adminDao = new AdminDAO();
        try {
            adminDao.update(
                    Double.parseDouble(request.getParameter("agency_com")),
                    Double.parseDouble(request.getParameter("vip_user_discount")),
                    Double.parseDouble(request.getParameter("user_discount")));
        } catch(MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "adsettings";
    }
}
