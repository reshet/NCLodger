package com.nclodger.logic;

import com.nclodger.domain.Order;
import com.nclodger.domain.PromoCode;
import com.nclodger.domain.User;
import com.nclodger.mail.MailConfirmation;
import com.nclodger.myexception.MyException;
import com.nclodger.publicdao.PromoCodeDAOInterface;
import com.nclodger.publicdao.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/4/13
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("userFacade")
public class UserFacade implements UserFacadeInterface{
    @Autowired MailConfirmation mailconfirm;
    @Autowired UserDaoInterface userdao;
    @Autowired PromoCodeDAOInterface promos;

    @Override
    public void saveOrder(Order ord) {
        //MailConfirmation mailconfirm =(MailConfirmation) ctx.getBean("mailconfirmation");
        User user = null;
        try {
            user = userdao.find(ord.getUserid());
            userdao.saveOrder(ord);
            if(user!=null)mailconfirm.sendMailOrder(user.getEmail(),ord.getFinal_price(),ord.getH());
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }

    @Override
    public void calculateFinalPrice(Order ord) {
        User user = null;
        try {
            user = userdao.find(ord.getUserid());
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(user!=null){
            Double price = 0.0;
            new PriceModifyer().modifyPriceByPromoCode(ord);
            /*PromoCode pm = ord.getPromo();
            if(pm!=null){
                price = ord.getH().getRoomBasePrice() - ord.getH().getRoomBasePrice()*pm.getDiscount();
            }else{
                price = ord.getH().getRoomBasePrice();
            }

            ord.setFinal_price(price);*/




        }
    }


}
