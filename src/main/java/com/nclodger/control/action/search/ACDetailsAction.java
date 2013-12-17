package com.nclodger.control.action.search;

import com.nclodger.control.action.Action;
import com.nclodger.dao.OrderDAO;
import com.nclodger.dao.SMDAO;
import com.nclodger.domain.User;
import com.nclodger.logic.PriceModifyer;
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
public class ACDetailsAction extends Action {
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
        String idDTOString = request.getParameter("idhotel");
        Integer idDTO = Integer.parseInt(idDTOString);
        HotelDTO h = findCurrentHotel(request,idDTOString);
        request.getSession().setAttribute("hotel",h);

        PriceModifyer pm = (PriceModifyer)ctx.getBean("pricerules");
        User user = (User)request.getSession().getAttribute("userfull");
        if(user!=null){
            pm.addDiscountToHotel(h,user);
        }

        if(request.getSession().getAttribute("utype") != null) {
            if(!(request.getSession().getAttribute("utype").toString()).equals("1")) {
                SMDAO smDao = new SMDAO();
                String idSmString =  request.getSession().getAttribute("idSm").toString();
                Integer idSm = Integer.parseInt(idSmString);

                OrderDAO orderDAO = new OrderDAO();
                Boolean isExistHotel = orderDAO.isExistHotelbyID(idDTO);
                Boolean isOccupied = false;
                if(isExistHotel) {
                    Integer idHotel = orderDAO.getIDHotelByintID(idDTO);

                    if(idHotel != null) {
                        isOccupied = smDao.isOccupied(idSm,idHotel);
                    }
                    if(isOccupied) {
                        request.setAttribute("isOccupied",true);
                    }
                    else {
                        request.setAttribute("isOccupied",false);
                    }

                }
                else {
                    request.setAttribute("isOccupied",false);
                }

            }
        }

        return "acdetails";
    }
}
