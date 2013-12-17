package com.nclodger.control.action.order;

import com.nclodger.control.action.Action;
import com.nclodger.dao.OrderDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.Hotel;
import com.nclodger.logic.PriceModifyer;
import com.nclodger.logic.UserFacadeInterface;
import com.nclodger.webservices.HotelDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 11/16/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderStartAction extends Action {
    HotelDTO findCurrentHotel(HttpServletRequest request,String id){
        Integer idd = Integer.parseInt(id);
       HotelDTO h = null;
       List<HotelDTO> hotelDTOs = (List<HotelDTO>) request.getSession().getAttribute("hotelDTOs");
        for(HotelDTO hh : hotelDTOs){
            if(hh.getId().equals(idd)){
                 h = hh;
                break;
            }
        }
        return h;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object emailObj = request.getSession().getAttribute("email");
        if(emailObj != null) {
            // Get bonus balance
            UserDao userDao = new UserDao();
            Double bonus = userDao.getBonusBalance(emailObj.toString());
            request.setAttribute("currentBonus", bonus);
        }
        return "orderstart";
    }
}
