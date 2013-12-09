package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.AdminDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.SManager;
import com.nclodger.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

            UserDao userDAO = (UserDao) ctx.getBean("userDAO");
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("allusers",users);
            return "adsettings";
        }
        else {
            return "home";
        }
    }
}
