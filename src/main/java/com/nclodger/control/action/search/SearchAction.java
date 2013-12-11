package com.nclodger.control.action.search;

import com.nclodger.control.action.Action;
import com.nclodger.dao.OrderDAO;

import com.nclodger.logic.PriceModifyer;
import com.nclodger.myexception.MyException;
import com.nclodger.webservices.ExpediaSearcher;
import com.nclodger.webservices.HotelDTO;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 31.10.13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class SearchAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //test log4j logging
        new MyException("search queried");

        ExpediaSearcher searcher = new ExpediaSearcher();
        // String state,String city, Date arrivalDate, Date departureDate, Integer adults, Integer response_count
        String checkin = request.getParameter("checkindate");
        String checkout = request.getParameter("checkoutdate");

        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String min_price = request.getParameter("min_price");
        String max_price = request.getParameter("max_price");
        String guests_children = request.getParameter("guests_children");
        String guests_adults = request.getParameter("guests_adults");
        /*String currency = request.getParameter("currency");*/
        String currency = "USD";

        request.getSession().setAttribute("checkindate",checkin);
        request.getSession().setAttribute("checkoutdate",checkout);
        request.getSession().setAttribute("country",country);
        request.getSession().setAttribute("city",city);
        request.getSession().setAttribute("min_price",min_price);
        request.getSession().setAttribute("max_price",max_price);
        request.getSession().setAttribute("guests_children",guests_children);
        request.getSession().setAttribute("guests_adults",guests_adults);
        request.getSession().setAttribute("currency", currency);

        String results = searcher.searchHotels(country,city,checkin,checkout,currency,
                Integer.parseInt(guests_adults),Integer.parseInt(guests_children));
        JSONObject resp = searcher.parseResults(results);
        List<HotelDTO> hotelDTOs = searcher.getHotelsList(resp);



        PriceModifyer mdf = (PriceModifyer)ctx.getBean("pricerules");
        //to slow implementation
        //mdf.addCommissionToHotels(hotelDTOs);
        mdf.addCommissionToHotelsBatch(hotelDTOs);

        //get Only free hotel on that time frame
        hotelDTOs = getFreeHotel(hotelDTOs,checkin,checkout);
        //price rule
        if(!max_price.equals("") && !min_price.equals("") ){
            try{
                hotelDTOs = getPriceRange(hotelDTOs,Double.parseDouble(min_price),Double.parseDouble(max_price));
            } catch (MyException ex) {
                request.setAttribute("error_message",ex.getMessage());
                return "exception";
            }
        }

        request.setAttribute("hotelDTOs", hotelDTOs);
        request.getSession().setAttribute("hotelDTOs", hotelDTOs);

        return "home";
    }

    //delete hotel from list which do not pass price range
    private List<HotelDTO> getPriceRange(List<HotelDTO> lst,double min, double max ) throws MyException {
        for(int i=lst.size()-1;i>=0;i--){
            if(lst.get(i).getRoomWithCommissionPrice()<min || lst.get(i).getRoomWithCommissionPrice()>max){
                lst.remove(i);
            }
        }
        return lst;
    }

    private List<HotelDTO> getFreeHotel(List<HotelDTO> lst,String checkin, String checkout){
        //get expedia room id from id_acc
        OrderDAO orderdao = new OrderDAO();
        int idAcc;
        int idExpAcc;
        try {
            for(int i=lst.size()-1;i>0;i--){
                   idExpAcc=lst.get(i).getRoomExpediaID();
                    idAcc=orderdao.getIDAccByExpID(idExpAcc);
                    if(orderdao.isExistOrderOnAcc(idAcc)){
                        if(!orderdao.isFreeAcc(idAcc,checkin,checkout));
                        lst.remove(i);
                    }
            }
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return lst;
    }
}
