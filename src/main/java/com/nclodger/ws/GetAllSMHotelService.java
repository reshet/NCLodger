package com.nclodger.ws;

import com.nclodger.additional.HotelManagingInfo;
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
 * User: pasha
 * Date: 12/9/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */

@Component("getallsmhotel")
public class GetAllSMHotelService extends AjaxService {
    @Autowired
    SMDaoInterface smdao;

    @Override
    public Serializable execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
        Integer idSM =(Integer) request.getSession().getAttribute("idSm");
        List<HotelManagingInfo> hlist = smdao.getAllOccupyHotelOfSMByID(idSM);
        return new ArrayList(hlist);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            //return null;
            return "exception";
        }

    }
}
