package com.nclodger.logic;

import com.nclodger.domain.InitialDiscount;
import com.nclodger.domain.Order;
import com.nclodger.domain.SManager;
import com.nclodger.domain.User;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.AdminDAOInterface;
import com.nclodger.publicdao.SMDaoInterface;
import com.nclodger.webservices.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/7/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("pricerules")
public class PriceModifyer {
    @Autowired
    SMDaoInterface smdao;
    @Autowired
    AdminDAOInterface addao;
   //@Autowired
    public PriceModifyer modifyPriceByPromoCode(Order ord){
        if(ord.getPromo()!=null){
            ord.setFinal_price(ord.getRoomSelectedPrice() - ord.getRoomSelectedPrice() * ord.getPromo().getDiscount());
        }else{
           ord.setFinal_price(ord.getRoomSelectedPrice());
        }
        return this;
    }
    public PriceModifyer modifyPriceByUserDiscount(Order ord,User user,User sm){

        return this;
    }
    public PriceModifyer addCommissionToHotels(List<HotelDTO> lst){
           for(HotelDTO hotel:lst){
               try {
                   List<HotelCommissionDTO> comms = smdao.getHotelCommissions(hotel.getId());
                   if(comms.size() == 0){
                       //initial commision get;
                       SManager sManager= addao.getCurDefaultCommAndDisc();
                       comms.add(new HotelCommissionDTO(0,sManager.getCommission()));
                       //hotel.getPrices().add(itPrice);

                   }
                   if(comms.size() == 1){
                       double commprice =  (double)Math.round(hotel.getRoomBasePrice()*(1+comms.get(0).getSmHotelCommission()/100.0));
                       hotel.setRoomWithCommissionPrice(commprice);
                       hotel.getPrices().put(comms.get(0).getSmID(), commprice);
                       //hotel.setPrice(hotel.getRoomWithCommissionPrice()+" "+hotel.getPriceCurrency());
                   }else{
                       for(HotelCommissionDTO com:comms){
                           double itCommision = com.getSmHotelCommission();
                           //double greatestCommision = comms.get(comms.size()-1).getSmHotelUserDisc();
                           double itPrice = Math.round(hotel.getRoomBasePrice()*(1+itCommision/100.0));
                           hotel.getPrices().put(com.getSmID(), itPrice);
                           //double greatestPrice = Math.round(hotel.getRoomBasePrice()*(1+greatestCommision/100.0));
                       }
                       //hotel.setPrice(lowestPrice+"-"+greatestPrice+" "+hotel.getPriceCurrency());

                   }
                   hotel.setComms(comms);
               } catch (MyException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }
               // hotel.setRoomWithCommissionPrice(20);
           }
       return this;
    }
    private HotelDTO currHotel(List<HotelDTO> list,int hotelid){
        HotelDTO h = null;
        for(HotelDTO hh : list){
            if(hh.getId().equals(hotelid)){
                h = hh;
                break;
            }
        }
        return h;
    }
    public PriceModifyer addCommissionToHotelsBatch(List<HotelDTO> lst){
        List<Integer> list = new LinkedList<Integer>();
        for(HotelDTO hotel:lst){
            list.add(hotel.getId());
        }
        try {
            SManager sManager= addao.getCurDefaultCommAndDisc();
            Map<Integer,List<HotelCommissionDTO>> comms_map = smdao.getHotelCommissionsBatch(list);
            //for(Integer hotelid:comms_map.keySet()){
            for(HotelDTO hotel:lst){

                List<HotelCommissionDTO> comms = new ArrayList<HotelCommissionDTO>();
                if(comms_map.containsKey(hotel.getId()))comms = comms_map.get(hotel.getId());
                //HotelDTO hotel = currHotel(lst,hotelid);
                if(comms.size() == 0){
                    //initial commision get;

                    comms.add(new HotelCommissionDTO(0,sManager.getCommission()));
                    //hotel.getPrices().add(itPrice);

                }
                if(comms.size() == 1){
                    double commprice =  (double)Math.round(hotel.getRoomBasePrice()*(1+comms.get(0).getSmHotelCommission()/100.0));
                    hotel.setRoomWithCommissionPrice(commprice);
                    hotel.getPrices().put(comms.get(0).getSmID(),commprice);
                    //hotel.setPrice(hotel.getRoomWithCommissionPrice()+" "+hotel.getPriceCurrency());
                }else{
                    for(HotelCommissionDTO com:comms){
                        double itCommision = com.getSmHotelCommission();
                        //double greatestCommision = comms.get(comms.size()-1).getSmHotelUserDisc();
                        double itPrice = Math.round(hotel.getRoomBasePrice()*(1+itCommision/100.0));
                        hotel.getPrices().put(com.getSmID(),itPrice);
                        //double greatestPrice = Math.round(hotel.getRoomBasePrice()*(1+greatestCommision/100.0));
                    }
                    //hotel.setPrice(lowestPrice+"-"+greatestPrice+" "+hotel.getPriceCurrency());

                }
                hotel.setComms(comms);
            }

        } catch (MyException e) {
            e.printStackTrace();
            //int b = 2;//To change body of catch statement use File | Settings | File Templates.
        }
            // hotel.setRoomWithCommissionPrice(20);

        return this;
    }
   /* public PriceModifyer addDiscountToHotels(List<HotelDTO> lst,User user){
        if(user.getIs_blocked()!=0)
        for(HotelDTO hotel:lst){
            try {
                List<HotelDiscountDTO> discs = smdao.getHotelDiscounts(hotel.getId());
                if(discs.size() == 0){
                    //initial commision get;
                    SManager sManager= addao.getCurDefaultCommAndDisc();
                    discs.add(new HotelDiscountDTO(0,sManager.getUser_discount(),sManager.getVip_discount()));
                }
                if(discs.size() == 1){
                    double discprice =  (double)Math.round(hotel.getRoomBasePrice()*(1+discs.get(0).getSmHotelCommission()/100.0));
                    //hotel.setRoomWithCommissionPrice(commprice);
                    hotel.getPrices().add(discprice);
                    //hotel.setPrice(hotel.getRoomWithCommissionPrice()+" "+hotel.getPriceCurrency());
                }else{
                    for(HotelCommissionDTO com:comms){
                        double itCommision = com.getSmHotelCommission();
                        //double greatestCommision = comms.get(comms.size()-1).getSmHotelUserDisc();
                        double itPrice = Math.round(hotel.getRoomBasePrice()*(1+itCommision/100.0));
                        hotel.getPrices().add(itPrice);
                        //double greatestPrice = Math.round(hotel.getRoomBasePrice()*(1+greatestCommision/100.0));
                    }
                    //hotel.setPrice(lowestPrice+"-"+greatestPrice+" "+hotel.getPriceCurrency());

                }
                hotel.setComms(comms);
            } catch (MyException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // hotel.setRoomWithCommissionPrice(20);
        }
        return this;
    }*/
       private double getDiscount(HotelDiscountDTO disc,boolean vip){
           if(vip){
               return disc.getSmHotelVipDisc();
           }
           else{
               return disc.getSmHotelUserDisc();
           }
       }
       public PriceModifyer addDiscountToHotel(HotelDTO hotel,User user){
        if(user.getIs_blocked()==0){

           boolean vip = (user.getVip() == 1);
            if(vip)
                hotel.setDiscount_type(HotelDTO.DISCOUNT_VIP);
            else
                hotel.setDiscount_type(HotelDTO.DISCOUNT_USER);

            try {
                List<HotelDiscountDTO> discs = smdao.getHotelDiscounts(hotel.getId());
                if(discs.size() == 0){
                    SManager sManager= addao.getCurDefaultCommAndDisc();
                    discs.add(new HotelDiscountDTO(0,sManager.getUser_discount(),sManager.getVip_discount(),"system"));
                }
                if(discs.size() == 1){
                    double discprice = 0;
                    double discount = getDiscount(discs.get(0),vip);
                    discprice = (double)Math.round(hotel.getRoomWithCommissionPrice()*(1-discount/100.0));
                    hotel.getPrices_disc().put(discs.get(0).getSmID(),discprice);
                }else{
                    for(HotelDiscountDTO com:discs){
                        double discount = getDiscount(com,vip);
                        double itPrice = Math.round(hotel.getPrices().get(com.getSmID())*(1-discount/100.0));
                        hotel.getPrices_disc().put(com.getSmID(),itPrice);
                    }
                }
                hotel.setDiscounts(discs);
            } catch (MyException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return this;
    }
}
