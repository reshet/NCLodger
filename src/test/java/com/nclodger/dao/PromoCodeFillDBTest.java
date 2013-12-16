package com.nclodger.dao;

import com.nclodger.domain.PromoCode;
import com.nclodger.myexception.MyException;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 12/17/13
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class PromoCodeFillDBTest extends TestCase {

    @Test
    public void testfillPromoCodeDB(){
        for(int i=0;i<100;i++){
            addPromocodeDB();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public void addPromocodeDB(){
        Date today = new Date();

        long offset = today.getTime();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date enddate = cal.getTime();
        long end = enddate.getTime();
        long diff = end - offset + 1;

        Date rand1 = new Date(offset+(long)(Math.random()*diff));

        long beginrand2 = rand1.getTime();
        diff = end - beginrand2 +1;
        Date rand2 = new Date(beginrand2+(long)(Math.random()*diff));
        PromoCodeDAO pcdao = new PromoCodeDAO();

        int idsm = getIDSM();
        String code = generatePromoCode(idsm);
        double discount = getDiscount();
        String formattedDate1= new SimpleDateFormat("MM/dd/yyyy").format(rand1);
        String formattedDate2 = new SimpleDateFormat("MM/dd/yyyy").format(rand2);
        PromoCode pc = new PromoCode(code,formattedDate1,formattedDate2,discount,0,idsm);
        try {
            pcdao.insert(pc);
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }




    }

    private String generatePromoCode(int idsm) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        StringBuilder sb = new StringBuilder("");
        final int N = 65;
        final int M = 25;
        sb.append(Character.toString((char) ((cal.get(Calendar.DAY_OF_MONTH) % M) + N)));
        sb.append(cal.get(Calendar.DAY_OF_MONTH) / M);
        sb.append(Character.toString((char) ((cal.get(Calendar.MONTH) % M) + N)));
        sb.append(Character.toString((char) ((cal.get(Calendar.YEAR) % M) + N)));
        sb.append(cal.get(Calendar.YEAR) / M);
        sb.append(Character.toString((char) ((cal.get(Calendar.HOUR_OF_DAY) % M) + N)));
        sb.append(Character.toString((char) ((cal.get(Calendar.MINUTE) % M) + N)));
        sb.append(cal.get(Calendar.MINUTE) / M);
        sb.append(Character.toString((char) ((cal.get(Calendar.SECOND) % M) + N)));
        sb.append(cal.get(Calendar.SECOND) / M);
        sb.append("-");
        sb.append(idsm);
        return sb.toString();
    }

    private int getIDSM()  {
        SMDAO smdao = new SMDAO();
        ArrayList<Integer> idsmlist = new ArrayList<Integer>();
        try {
            idsmlist = (ArrayList<Integer>) smdao.getIDSM();
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int index = (int) (Math.random() * (idsmlist.size() -1));
        return idsmlist.get(index);

    }

    private Double getDiscount(){
        int min =1;
        int max =33;
        int ran =min + (int)(Math.random() * ((max - min) + 1));
        return ran/100.0;
    }
}
