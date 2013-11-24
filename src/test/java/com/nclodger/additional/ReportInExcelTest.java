package com.nclodger.additional;

import com.nclodger.dao.Hotel;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/24/13
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */


public class ReportInExcelTest extends TestCase {

    @Test
    public void testcreateMostPopularHotel() throws IOException {
        try{
            ArrayList<HotelTotalOrder> hList = new ArrayList<HotelTotalOrder>();

            Hotel h1 = new Hotel(1,"hilton",35,25,5,2,"London","Great Britain");
            Hotel h2 = new Hotel(2,"Plaza",27,45,4,1,"Kiev","Ukraine");
            hList.add(new HotelTotalOrder(h1,150));
            hList.add(new HotelTotalOrder(h2,222));
            ReportInExcel re = new ReportInExcel();
            re.createMostPopularHotel(hList);
        }
            catch (Exception e) {
            e.getStackTrace();
        }

    }

}
