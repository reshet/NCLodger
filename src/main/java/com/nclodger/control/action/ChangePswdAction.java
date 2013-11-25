package com.nclodger.control.action;

import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 23.11.13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public class ChangePswdAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();
        int idUser = userDao.getUserId(request.getSession().getAttribute("email").toString());
        Users u = new Users(request.getParameter("password").toString(),idUser);
        userDao.updatePswd(u);
        request.setAttribute("iduser",u.getPswd());
        return "ussettings";
    }
}
