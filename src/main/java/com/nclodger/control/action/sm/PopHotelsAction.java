package com.nclodger.control.action.sm;

import com.nclodger.additional.HotelTotalOrder;
import com.nclodger.additional.ReportInExcel;
import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/21/13
 * Time: 1:02 AM
 */
public class PopHotelsAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SMDAO smdao = new SMDAO();

        ArrayList<HotelTotalOrder> hList = new ArrayList<HotelTotalOrder>();
        String start = request.getParameter("startDate");
        String end  = request.getParameter("endDate");
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date startdate = formatter.parse(start);
        Date enddate = formatter.parse(end);
        hList = smdao.sortHotelbyPopularWithTimeFrame(startdate,enddate);

        ReportInExcel re = new ReportInExcel();
        re.createMostPopularHotel(hList);
        //TODO: make link in jsp to download xls file

        return "smsettings";
    }
}
