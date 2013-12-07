package com.nclodger.dao;

import com.nclodger.domain.Hotel;
import com.nclodger.myexception.MyException;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/7/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderDAOTest extends TestCase {
    @Test
    public void testinsertHotel() throws MyException{
/*        public Hotel(String name_hotel, String country, String city, int category, double loc_lng, double loc_lat,int intID) {
            this.name_hotel = name_hotel;
            this.country = country;
            this.city = city;
            this.loc_lng = loc_lng;
            this.loc_lat = loc_lat;
            this.category = category;
            this.intID = intID;
        }*/
       OrderDAO oDAO = new OrderDAO();
        Hotel h = new Hotel("Necrominicon","Necrocountry","Necrocity",0,0,5,123);
        boolean test =   oDAO.insertHotel(h);
        assertEquals(test,true);



    }
}
