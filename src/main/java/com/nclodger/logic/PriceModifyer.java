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

import java.util.List;

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
    public PriceModifyer modifyPriceByPromoCode(Order ord){
        if(ord.getPromo()!=null){
            ord.setFinal_price(ord.getH().getRoomBasePrice() - ord.getH().getRoomBasePrice() * ord.getPromo().getDiscount());
        }else{
           ord.setFinal_price(ord.getH().getRoomBasePrice());
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
                   }
                   if(comms.size() == 1){
                       hotel.setRoomWithCommissionPrice((double)Math.round(hotel.getRoomBasePrice()*(1+comms.get(0).getSmHotelCommission()/100.0)));
                       hotel.setPrice(hotel.getRoomWithCommissionPrice()+" "+hotel.getPriceCurrency());
                   }else{
                       double lowestCommision = comms.get(0).getSmHotelCommission();
                       double greatestCommision = comms.get(comms.size()-1).getSmHotelCommission();
                       double lowestPrice = Math.round(hotel.getRoomBasePrice()*(1+lowestCommision/100.0));
                       double greatestPrice = Math.round(hotel.getRoomBasePrice()*(1+greatestCommision/100.0));
                       hotel.setPrice(lowestPrice+"-"+greatestPrice+" "+hotel.getPriceCurrency());

                   }
                   hotel.setComms(comms);
               } catch (MyException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }
               // hotel.setRoomWithCommissionPrice(20);
           }
       return this;
    }
}
