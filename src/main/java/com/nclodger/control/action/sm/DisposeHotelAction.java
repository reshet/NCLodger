package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;
import com.nclodger.webservices.HotelDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 08.12.13
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class DisposeHotelAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HotelDTO hotelDTO = (HotelDTO) request.getSession().getAttribute("hotel");
        Integer idHotelDTO = hotelDTO.getId();

        SMDAO smdao = new SMDAO();
        Integer idHotel = smdao.getIdHotelByIdDTO(idHotelDTO);
        Integer idSm = Integer.parseInt(request.getSession().getAttribute("idSm").toString());
        smdao.deleteHotelManager(idHotel, idSm);

        return "smsettings";
    }
}
