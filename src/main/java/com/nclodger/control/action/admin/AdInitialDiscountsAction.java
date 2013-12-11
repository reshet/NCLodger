package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
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
public class AdInitialDiscountsAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If User is not authorized or not Administrator
        if(request.getSession().getAttribute("utype") == null ||
                !(request.getSession().getAttribute("utype").toString()).equals("3")) {
            return "home";
        }

        AdminDAO adminDao = new AdminDAO();
        try {
            adminDao.update(
                    Double.parseDouble(request.getParameter("agency_com")),
                    Double.parseDouble(request.getParameter("vip_user_discount")),
                    Double.parseDouble(request.getParameter("user_discount")));
            request.getSession().setAttribute("defcurcom",request.getParameter("agency_com"));
            request.getSession().setAttribute("defcurdisc",request.getParameter("user_discount"));
            request.getSession().setAttribute("defcurvipdisc",request.getParameter("vip_user_discount"));
        } catch(MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "adsettings";
    }
}
