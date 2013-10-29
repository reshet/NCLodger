package com.nclodger.webservices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/29/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExpediaSearcher {
    public static String ean_key = "eeay5hbnwsh6ghjfagwcvxus";
    public static String base_urlParams = "?cid=55505&minorRev=99&apiKey="+ean_key+"&locale=en_US&currencyCode=USD&xml=";
    public static String base_urlString = "https://api.eancdn.com/ean-services/rs/hotel/v3/";
    public String searchHotels(String state, String city, String arrival, String departure, int adults, int limit) {
        String xml_body = "<HotelListRequest>\n" +
                "    <city>"+city+"</city>\n" +
                "    <countryCode>"+state+"</countryCode>\n" +
                "    <arrivalDate>"+arrival+"</arrivalDate>\n" +
                "    <departureDate>"+departure+"</departureDate>\n" +
                "    <RoomGroup>\n" +
                "        <Room>\n" +
                "            <numberOfAdults>"+adults+"</numberOfAdults>\n" +
                "        </Room>\n" +
                "    </RoomGroup>\n" +
                "    <numberOfResults>"+limit+"</numberOfResults>\n" +
                "</HotelListRequest>";
        URLConnection conn = null;
        try {
            String urlString = base_urlString+ "list"+base_urlParams+ URLEncoder.encode(xml_body, "ISO-8859-1");
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
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public JSONObject parseResults(String results) {
        if(results == null) return null;
        JSONTokener tokener = new JSONTokener(results);
        try {
            JSONObject root = new JSONObject(tokener);
            return root;
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public Map<Integer, String> getHotelsMap(JSONObject resp) {
        Map<Integer,String> map_hotels = new TreeMap<Integer, String>();
        JSONArray hotels = null;
        if(resp == null) return map_hotels;
        try {
            hotels = resp.getJSONObject("HotelListResponse").getJSONObject("HotelList").getJSONArray("HotelSummary");

        for(int i = 0; i < hotels.length();i++){
            JSONObject hotel = hotels.getJSONObject(i);
            Integer id = hotel.getInt("hotelId");
            String name = hotel.getString("name");
            map_hotels.put(id,name);
            //System.out.println("ID: "+id+", name: "+name);
        }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return map_hotels;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Hotel> getHotelsList(JSONObject resp) {
        List<Hotel> list = new ArrayList<Hotel>();
        JSONArray hotels = null;
        if(resp == null) return list;
        try {
            hotels = resp.getJSONObject("HotelListResponse").getJSONObject("HotelList").getJSONArray("HotelSummary");

            for(int i = 0; i < hotels.length();i++){
                JSONObject hotel = hotels.getJSONObject(i);
                Integer id = hotel.getInt("hotelId");
                String name = hotel.getString("name");
                Double loc_lat = hotel.getDouble("latitude");
                Double loc_lng = hotel.getDouble("longitude");

                //map_hotels.put(id,name);
                //System.out.println("ID: "+id+", name: "+name);
                list.add(new Hotel(id,name,loc_lat,loc_lng));
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return list;  //To change body of created methods use File | Settings | File Templates.
    }
}
