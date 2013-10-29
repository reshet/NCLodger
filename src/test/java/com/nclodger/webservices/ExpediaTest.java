package com.nclodger.webservices;

import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/29/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExpediaTest extends TestCase{
    public static String ean_key = "eeay5hbnwsh6ghjfagwcvxus";
    public static String base_urlParams = "?cid=55505&minorRev=99&apiKey="+ean_key+"&locale=en_US&currencyCode=USD&xml=";
    public static String base_urlString = "https://api.eancdn.com/ean-services/rs/hotel/v3/";

    @Test
    public void testPingRequest(){
        String test_msg = "This my test case message should be echoed back to you.";
        String xml_body = "<PingRequest>\n" +
                "<echo>"+test_msg+"</echo>\n" +
                "</PingRequest>";
        URLConnection conn = null;
        try {
            String urlString = base_urlString+ "ping"+base_urlParams+URLEncoder.encode(xml_body,"ISO-8859-1");
            URL url = new URL(urlString);
            conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            JSONTokener tokener = new JSONTokener(result.toString());
            JSONObject root = new JSONObject(tokener);
            String response = root.getJSONObject("PingResponse").getString("echo");
            System.out.println(response);
            assertEquals(test_msg, response);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    @Test
    public void testSandboxHotelSearch(){
        String test_msg = "4";
        String xml_body = "<HotelListRequest>\n" +
                "    <city>Seattle</city>\n" +
                "    <stateProvinceCode>WA</stateProvinceCode>\n" +
                "    <countryCode>US</countryCode>\n" +
                "    <arrivalDate>11/28/2013</arrivalDate>\n" +
                "    <departureDate>11/30/2013</departureDate>\n" +
                "    <RoomGroup>\n" +
                "        <Room>\n" +
                "            <numberOfAdults>2</numberOfAdults>\n" +
                "        </Room>\n" +
                "    </RoomGroup>\n" +
                "    <numberOfResults>"+test_msg+"</numberOfResults>\n" +
                "</HotelListRequest>";
        URLConnection conn = null;
        try {
            String urlString = base_urlString+ "list"+base_urlParams+URLEncoder.encode(xml_body,"ISO-8859-1");
            URL url = new URL(urlString);
            conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            //System.out.println(result);
            JSONTokener tokener = new JSONTokener(result.toString());
            JSONObject root = new JSONObject(tokener);
            Integer response = root.getJSONObject("HotelListResponse").getInt("numberOfRoomsRequested");
            JSONArray hotels = root.getJSONObject("HotelListResponse").getJSONObject("HotelList").getJSONArray("HotelSummary");
            //System.out.println(response);
            assertEquals(1,(int)response);
            assertEquals(4,hotels.length());
            for(int i = 0; i < hotels.length();i++){
                JSONObject hotel = hotels.getJSONObject(i);
                Integer id = hotel.getInt("hotelId");
                String name = hotel.getString("name");

                //System.out.println("ID: "+id+", name: "+name);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    @Test
    public void testHotelSearchMethod(){
        ExpediaSearcher searcher = new ExpediaSearcher();
        //                                     String state,String city, Date arrivalDate, Date departureDate, Integer adults, Integer response_count
        String results = searcher.searchHotels("US","Seattle","11/28/2013","11/30/2013",2,4);
        JSONObject resp = searcher.parseResults(results);
        try {
            Integer response = resp.getJSONObject("HotelListResponse").getInt("numberOfRoomsRequested");
            assertEquals(1,(int)response);
            Map<Integer,String> hotels = searcher.getHotelsMap(resp);
            for(Integer key:hotels.keySet()){
                //System.out.println("ID: "+key+", name: "+hotels.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Test
    public void testHotelSearchMethodKiev(){
        ExpediaSearcher searcher = new ExpediaSearcher();
        //                                     String state,String city, Date arrivalDate, Date departureDate, Integer adults, Integer response_count
        String results = searcher.searchHotels("UA","Kiev","11/28/2013","11/30/2013",2,4);
        JSONObject resp = searcher.parseResults(results);
        try {
            Integer response = resp.getJSONObject("HotelListResponse").getInt("numberOfRoomsRequested");
            assertEquals(1,(int)response);
            Map<Integer,String> hotels = searcher.getHotelsMap(resp);
            for(Integer key:hotels.keySet()){
                //System.out.println("ID: "+key+", name: "+hotels.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Test
    public void testHotelListMethodKiev(){
        ExpediaSearcher searcher = new ExpediaSearcher();
        //                                     String state,String city, Date arrivalDate, Date departureDate, Integer adults, Integer response_count
        String results = searcher.searchHotels("UA","Kiev","11/28/2013","11/30/2013",2,4);
        System.out.println(results);
        JSONObject resp = searcher.parseResults(results);
        try {
            Integer response = resp.getJSONObject("HotelListResponse").getInt("numberOfRoomsRequested");
            assertEquals(1,(int)response);
            List<Hotel> hotels = searcher.getHotelsList(resp);
            for(Hotel hotel:hotels){
                System.out.println(hotel.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
