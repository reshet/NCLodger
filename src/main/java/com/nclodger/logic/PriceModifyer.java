package com.nclodger.logic;

import com.nclodger.domain.Order;
import com.nclodger.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/7/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PriceModifyer {
    public PriceModifyer modifyPriceByPromoCode(Order ord){
        if(ord.getPromo()!=null){
            ord.setFinal_price(ord.getH().getRoomPrice() - ord.getH().getRoomPrice() * ord.getPromo().getDiscount());
        }else{
           ord.setFinal_price(ord.getH().getRoomPrice());
        }
        return this;
    }
    public PriceModifyer modifyPriceByUserDiscount(Order ord,User user,User sm){

        return this;
    }
}
