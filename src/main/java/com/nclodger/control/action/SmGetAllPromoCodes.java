package com.nclodger.control.action;

import com.nclodger.dao.PromoCode;
import com.nclodger.dao.PromoCodeDAO;
import com.nclodger.dao.SMDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav Dmytruk
 * Date: 22.11.13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class SmGetAllPromoCodes implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SMDao smDao = new SMDao();
            PromoCodeDAO pcdao = new PromoCodeDAO();
            int idSm = smDao.getSmanagerId(request.getSession().getAttribute("email").toString());
            List<PromoCode> pclist = pcdao.getAllPCbySMid(idSm);
            request.setAttribute("allpromocodes",pclist);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "smsettings";
    }

}
