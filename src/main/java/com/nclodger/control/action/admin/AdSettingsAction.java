package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.AdminDAO;
import com.nclodger.domain.SManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 05.11.13
 * Time: 19:31
 */
public class AdSettingsAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("utype")!=null
                && (Integer)request.getSession().getAttribute("utype") == 3) {
            AdminDAO adminDAO = new AdminDAO();
            SManager sm = adminDAO.getCurDefaultCommAndDisc();
            request.getSession().setAttribute("defcurcom",sm.getCommission());
            request.getSession().setAttribute("defcurdisc",sm.getUser_discount());
            request.getSession().setAttribute("defcurvipdisc",sm.getVip_discount());
            return "adsettings";
        }
        else {
            return "home";
        }
    }
}
