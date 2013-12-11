package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;
import com.nclodger.domain.PromoCode;
import com.nclodger.dao.PromoCodeDAO;
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
public class SmGetAllPromoCodes extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If User is not Sales Manager or Administrator
        if(request.getSession().getAttribute("utype") == null ||
                (request.getSession().getAttribute("utype").toString()).equals("1")) {
            return "home";
        }

        try {
            SMDAO smDao = new SMDAO();
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
