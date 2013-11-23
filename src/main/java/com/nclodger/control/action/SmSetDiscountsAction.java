package com.nclodger.control.action;

import com.nclodger.dao.SMDao;
import com.nclodger.myexception.MyException;

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
        SMDao smDao = new SMDao();
        try {
            int idSm = smDao.getSmanagerId(request.getSession().getAttribute("email").toString());
            smDao.setCommAndDiscounts(idSm,
                    Double.parseDouble(request.getParameter("agency_com")),
                    Double.parseDouble(request.getParameter("vip_user_discount")),
                    Double.parseDouble(request.getParameter("user_discount")));
        } catch(MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "smsettings";
    }
}
