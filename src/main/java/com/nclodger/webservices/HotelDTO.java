package com.nclodger.webservices;

import com.nclodger.logic.HotelCommissionDTO;
import com.nclodger.logic.HotelDiscountDTO;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/29/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class HotelDTO implements Serializable {
    private final Integer id;
    private final String name;
    private Double loc_lat;
    private Double loc_lng;
    private String image_url;
    private String address;
    private String price;
    private String priceCurrency;
    private int roomExpediaID;
    private String roomType;
    private String roomOccupancy;
    private Double roomBasePrice;
    private Double roomWithCommissionPrice;
    private Double roomWithDiscountPrice;
    private List<HotelCommissionDTO> comms = new ArrayList<HotelCommissionDTO>();
    private Map<Integer,Double> prices = new HashMap<Integer,Double>();
    private List<HotelDiscountDTO> discounts = new ArrayList<HotelDiscountDTO>();
    private Map<Integer,Double> prices_disc = new HashMap<Integer,Double>();
    private String discount_type = "";
    public static String DISCOUNT_USER = "registered user discount";
    public static String DISCOUNT_VIP = "vip user discount";




    public HotelDTO(Integer id, String name, Double loc_lat, Double loc_lng) {
        this.id = id;
        this.name = name;
        this.loc_lat = loc_lat;
        this.loc_lng = loc_lng;
    }
    @Override
    public String toString(){
       return "ID: "+ getId() +", name: "+ getName()+", location: "+loc_lat+","+loc_lng;
    }

    public Double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(Double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public Double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(Double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        String price = "";
        List<Double> prcs = new ArrayList<Double>();
        for(Integer smid:getPrices().keySet()){
            Double pr = getPrices().get(smid);
            prcs.add(pr);
        }
        Collections.sort(prcs);
        if(prcs.size()>1){
           price = prcs.get(0)+"-"+ prcs.get(prcs.size() - 1)+" "+getPriceCurrency();
        }else{
            price = prcs.get(0)+" "+getPriceCurrency();
        }
        return price;
    }

    /*public void setPrice(String price) {
        this.price = price;
    }*/

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomOccupancy(String roomOccupancy){
        this.roomOccupancy = roomOccupancy;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomOccupancy() {
        return roomOccupancy;
    }

    public Double getRoomBasePrice() {
        return roomBasePrice;
    }

    public void setRoomBasePrice(Double roomBasePrice) {
        this.roomBasePrice = roomBasePrice;
    }

    public Double getRoomWithCommissionPrice() {
        return roomWithCommissionPrice;
    }

    public void setRoomWithCommissionPrice(Double roomWithCommissionPrice) {
        this.roomWithCommissionPrice = roomWithCommissionPrice;
    }

    public Double getRoomWithDiscountPrice() {
        return roomWithDiscountPrice;
    }

    public void setRoomWithDiscountPrice(Double roomWithDiscountPrice) {
        this.roomWithDiscountPrice = roomWithDiscountPrice;
    }

    public List<HotelCommissionDTO> getComms() {
        return comms;
    }

    public void setComms(List<HotelCommissionDTO> comms) {
        this.comms = comms;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }



    public int getRoomExpediaID() {
        return roomExpediaID;
    }

    public void setRoomExpediaID(int roomExpediaID) {
        this.roomExpediaID = roomExpediaID;
    }

    public List<HotelDiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<HotelDiscountDTO> discounts) {
        this.discounts = discounts;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public Map<Integer, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<Integer, Double> prices) {
        this.prices = prices;
    }

    public Map<Integer, Double> getPrices_disc() {
        return prices_disc;
    }

    public void setPrices_disc(Map<Integer, Double> prices_disc) {
        this.prices_disc = prices_disc;
    }
}
