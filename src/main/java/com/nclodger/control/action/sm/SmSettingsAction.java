package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.SManager;
import com.nclodger.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 30.10.13
 * Time: 12:36
 */

public class SmSettingsAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If User is not Sales Manager or Administrator
        if(request.getSession().getAttribute("utype") == null ||
                (request.getSession().getAttribute("utype").toString()).equals("1")) {
            return "home";
        }

        SMDAO smDao;
        SManager sm;
        if(request.getSession().getAttribute("utype")!=null
                && (Integer)request.getSession().getAttribute("utype") == 2) {
            smDao = new SMDAO();
            sm = smDao.getCurrentCommAndDiscounts(request.getSession().getAttribute("email").toString());
            request.getSession().setAttribute("curcom",(int)sm.getCommission());
            request.getSession().setAttribute("curdisc",(int)sm.getUser_discount());
            request.getSession().setAttribute("curvipdisc",(int)sm.getUser_discount());

            UserDao userDAO = (UserDao) ctx.getBean("userDAO");
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("allusers",users);
            return "smsettings";
        }
        else {
            return "home";
        }
    }
}
