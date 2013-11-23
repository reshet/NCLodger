package com.nclodger.control.action;

import com.nclodger.dao.SMDao;
import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/21/13
 * Time: 1:02 AM
 */
public class getPopHotelsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SMDao smdao = new SMDao();
    //    List<String> hotel = (List<String>)smdao.sortHotelbyPopular();
    //    request.setAttribute("mostpophotel",hotel);

        return "smsettings";
    }
}
