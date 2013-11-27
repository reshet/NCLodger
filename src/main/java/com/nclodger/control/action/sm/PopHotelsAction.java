package com.nclodger.control.action.sm;

import com.nclodger.additional.HotelTotalOrder;
import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/21/13
 * Time: 1:02 AM
 */
public class PopHotelsAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SMDAO smdao = new SMDAO();


        ArrayList<HotelTotalOrder> alist = new ArrayList<HotelTotalOrder>();
        alist = smdao.sortHotelbyPopular();

        request.setAttribute("mostpophotel",alist);
       //


        return "smsettings";
    }
}
