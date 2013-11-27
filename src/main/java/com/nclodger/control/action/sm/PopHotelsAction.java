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
/*        public Hotel(int id_hotel,String name_hotel, double loc_lal,double loc_lng,
        int category, int id_sm, String city, String country)*/
/*        alist.add(new HotelTotalOrder(new Hotel(1,"Plaza",35.2,54,4,2,"Kiev","Ukraine"),3));
        alist.add(new HotelTotalOrder(new Hotel(2,"Hilton",32.2,542,5,5,"London","Great Britan"),2));*/
/*        alist.add(new HotelTotalOrder("Plaza","Kiev","Ukraine",3));
        alist.add(new HotelTotalOrder("Hilton","London","Great Britaine",2));*/
        request.setAttribute("mostpophotel",alist);
       //


        return "smsettings";
    }
}
