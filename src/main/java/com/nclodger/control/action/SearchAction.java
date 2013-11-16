package com.nclodger.control.action;

import com.nclodger.webservices.ExpediaSearcher;
import com.nclodger.webservices.Hotel;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 31.10.13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class SearchAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpediaSearcher searcher = new ExpediaSearcher();
        // String state,String city, Date arrivalDate, Date departureDate, Integer adults, Integer response_count
        String checkin = request.getParameter("checkindate");
        String checkout = request.getParameter("checkoutdate");

                /*
        String checkin = request.getParameter("checkin_month") + "/" +
                (request.getParameter("checkin_day")) + "/" +
                (request.getParameter("checkin_year"));
        String checkout = request.getParameter("checkout_month") + "/" +
                (request.getParameter("checkout_day")) + "/" +
                (request.getParameter("checkout_year"));*/
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String min_price = request.getParameter("min_price");
        String max_price = request.getParameter("max_price");
        String guests_children = request.getParameter("guests_children");
        String guests_adults = request.getParameter("guests_adults");
        String currency = request.getParameter("currency");


        request.getSession().setAttribute("checkindate",checkin);
        request.getSession().setAttribute("checkoutdate",checkout);
        request.getSession().setAttribute("country",country);
        request.getSession().setAttribute("city",city);
        request.getSession().setAttribute("min_price",min_price);
        request.getSession().setAttribute("max_price",max_price);
        request.getSession().setAttribute("guests_children",guests_children);
        request.getSession().setAttribute("guests_adults",guests_adults);
        request.getSession().setAttribute("currency", currency);


        //String results = searcher.searchHotels(country,city,"10/5/2013","10/6/2013",2,10);
        String results = searcher.searchHotels(country,city,"11/28/2013","11/30/2013",2,10);
        JSONObject resp = searcher.parseResults(results);
        List<Hotel> hotels = searcher.getHotelsList(resp);
        request.setAttribute("servlet_value",hotels);
        /*try {
            //Integer eResponse = resp.getJSONObject("HotelListResponse").getInt("numberOfRoomsRequested");
            List<Hotel> hotels = searcher.getHotelsList(resp);
            request.setAttribute("servlet_value",hotels);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return "home";
    }
}
