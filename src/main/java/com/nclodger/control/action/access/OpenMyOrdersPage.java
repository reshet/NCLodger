package com.nclodger.control.action.access;

import com.nclodger.additional.BookingViewing;
import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 09.12.13
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class OpenMyOrdersPage extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("email") == null) {
            return "home";
        }
        else {

            String email = request.getSession().getAttribute("email").toString();
            UserDao udao = new UserDao();
            int id = udao.getUserId(email);

            ArrayList<BookingViewing> bookinglist = new ArrayList<BookingViewing>();
            bookinglist = udao.getPastOrder(id);
            request.setAttribute("bookinglist",bookinglist);

            return "myorders";
        }
    }
}
