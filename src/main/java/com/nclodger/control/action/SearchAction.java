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
        String checkin = request.getParameter("checkin_month") + "/" +
                (request.getParameter("checkin_day")) + "/" +
                (request.getParameter("checkin_year"));
        String checkout = request.getParameter("checkout_month") + "/" +
                (request.getParameter("checkout_day")) + "/" +
                (request.getParameter("checkout_year"));
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String results = searcher.searchHotels(country,city,checkin,checkout,2,10);
        //System.out.println(results);
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
