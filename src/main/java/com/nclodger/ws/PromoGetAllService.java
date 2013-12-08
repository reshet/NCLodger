package com.nclodger.ws;

import com.nclodger.dao.PromoCodeDAO;
import com.nclodger.dao.SMDAO;
import com.nclodger.domain.PromoCode;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.PromoCodeDAOInterface;
import com.nclodger.publicdao.SMDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/8/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("getallpromocodes")
public class PromoGetAllService extends AjaxService{
    @Autowired
    SMDaoInterface smdao;
    @Autowired
    PromoCodeDAOInterface pcdao;
    @Override
    public Serializable execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int idSm = smdao.getSmanagerId(request.getSession().getAttribute("email").toString());
            List<PromoCode> pclist = pcdao.getAllPCbySMid(idSm);
            return new ArrayList(pclist);
            //return pclist;
            //request.setAttribute("allpromocodes",pclist);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            //return null;
            return "exception";
        }
        //return "simple text";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
