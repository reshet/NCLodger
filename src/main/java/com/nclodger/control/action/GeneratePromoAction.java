package com.nclodger.control.action;

import com.nclodger.dao.PromoCode;
import com.nclodger.dao.SMDao;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 19.11.13
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public class GeneratePromoAction implements Action{

    private void generatePromoCode(HttpServletRequest request, HttpServletResponse response) throws MyException {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        StringBuilder sb = new StringBuilder("");
        sb.append(Character.toString((char) (cal.get(Calendar.DAY_OF_MONTH)+ 64)));
        sb.append(Character.toString((char) ((cal.get(Calendar.MONTH) % 26) + 64)));
        sb.append(Character.toString((char) ((cal.get(Calendar.YEAR) % 25) + 65)));
        sb.append(Character.toString((char) ((cal.get(Calendar.HOUR_OF_DAY) % 25) + 65)));
        sb.append(Character.toString((char) ((cal.get(Calendar.MINUTE) % 25) + 65)));
        sb.append(Character.toString((char) ((cal.get(Calendar.SECOND) % 25) + 65)));
        sb.append("-");
        String smEmail = request.getSession().getAttribute("email").toString();
        SMDao smDao;
        try {
            smDao = new SMDao();
            int idSm = smDao.getSmanagerId(smEmail);
            sb.append(idSm);
            request.getSession().setAttribute("promo_code",sb.toString());
        } catch (MyException ex) {
            throw new MyException(ex.getMessage());
        }
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        generatePromoCode(request,response);
        return "smsettings";
    }
}
