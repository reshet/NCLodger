package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.OrderDAO;
import com.nclodger.dao.SMDAO;
import com.nclodger.domain.Hotel;
import com.nclodger.webservices.HotelDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 07.12.13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class OccupyHotelAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If User is not Sales Manager or Administrator
        if(request.getSession().getAttribute("utype") == null ||
                (request.getSession().getAttribute("utype").toString()).equals("1")) {
            return "home";
        }

        HotelDTO hotelDTO = (HotelDTO) request.getSession().getAttribute("hotel");
        Integer idHotelDTO = hotelDTO.getId();

        SMDAO smdao = new SMDAO();
        Integer idSm = Integer.parseInt(request.getSession().getAttribute("idSm").toString());
        Integer commission = smdao.getCommission(idSm);
        OrderDAO orderDAO = new OrderDAO();
        Boolean bool = orderDAO.isExistHotelbyID(idHotelDTO);
        if(bool) {
            Integer idHotel = smdao.getIdHotelByIdDTO(idHotelDTO);
            smdao.insertHotelManager(idHotel, idSm, commission);
        }
        else {
            orderDAO.insertHotel(new Hotel(hotelDTO.getName(),
                    request.getSession().getAttribute("country").toString(),
                    request.getSession().getAttribute("city").toString(),
                    5,hotelDTO.getLoc_lat(),hotelDTO.getLoc_lng(),idHotelDTO));
            Integer idHotel = smdao.getIdHotelByIdDTO(idHotelDTO);
            smdao.insertHotelManager(idHotel, Integer.parseInt(request.getSession().getAttribute("idSm").toString()), commission);
        }
        return "smsettings";
    }
}
