package com.nclodger.control.action.search;

import com.nclodger.control.action.Action;
import com.nclodger.dao.OrderDAO;
import com.nclodger.dao.SMDAO;
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
        String idHotelDTO = request.getParameter("idhotel");
        HotelDTO h = findCurrentHotel(request,idHotelDTO);
        request.getSession().setAttribute("hotel",h);

        // If user is logged in and he's Sales Manager
        if(request.getSession().getAttribute("email") != null) {
            if(Integer.parseInt(request.getSession().getAttribute("utype").toString()) != 1) {
                Integer intIdHotelDTO = Integer.parseInt(idHotelDTO);
                SMDAO smDao = new SMDAO();

                OrderDAO orderDAO = new OrderDAO();
                Boolean exists = orderDAO.isExistHotelbyID(intIdHotelDTO);
                // If there is already hotel with such idHotelDTO in database (table HOTEL)
                if(exists) {
                    // Check if this hotel is occupied by this Sales Manager
                    Integer idHotel = smDao.getIdHotelByIdDTO(intIdHotelDTO);
                    int idSm = Integer.parseInt(request.getSession().getAttribute("idSm").toString());
                    Boolean occupied = smDao.isOccupied(idSm,idHotel);
                    if(occupied) {
                        request.setAttribute("isOccupied",true);
                    }
                    else {
                        request.setAttribute("isOccupied",false);
                    }
                }
                // If not exists then it couldn't be occupied
                else {
                    request.setAttribute("isOccupied",false);
                }

            }
        }
        return "acdetails";
    }
}
