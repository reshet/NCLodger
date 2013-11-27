package com.nclodger.control.action.admin;

import com.nclodger.control.action.Action;
import com.nclodger.dao.AdminDAO;
import com.nclodger.domain.Hotel;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 27.11.13
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public class AddHotelAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Hotel hotel;
        AdminDAO adminDAO;
        int category = Integer.parseInt(request.getParameter("category"));;
        double lat = Double.parseDouble(request.getParameter("latitude"));;
        double lng = Double.parseDouble(request.getParameter("longitude"));;
        try {
            hotel = new Hotel(request.getParameter("hotelname"), request.getParameter("country"),
                 request.getParameter("city"), category, lat, lng);
            adminDAO = new AdminDAO();
            adminDAO.insertHotel(hotel);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }
        return "adsettings";
    }
}
