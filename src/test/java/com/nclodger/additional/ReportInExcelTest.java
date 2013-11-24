package com.nclodger.additional;

import com.nclodger.dao.Accommodation;
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


    public void testcreateMostValuableAccomodation() throws IOException {
        try{
            ArrayList<AccommodationTotalValue> aList = new ArrayList<AccommodationTotalValue>();
            Accommodation a1 = new Accommodation(1,1,231,3,"luxe");
            Accommodation a2 = new Accommodation(2,3,100,3,"standart");

            aList.add(new AccommodationTotalValue(a1,"Plaza","Kiev","Ukraine",240));
            aList.add(new AccommodationTotalValue(a2,"Plaza","Kiev","Ukraine",195));
            ReportInExcel re = new ReportInExcel();
            re.createMostValuableAccomodation(aList);
        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }

}
