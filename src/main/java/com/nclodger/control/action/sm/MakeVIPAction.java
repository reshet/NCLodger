package com.nclodger.control.action.sm;

import com.nclodger.control.action.Action;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.User;
import com.nclodger.mail.EmailNotification;
import com.nclodger.myexception.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 20.11.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class MakeVIPAction extends Action {
    @Autowired
    EmailNotification emailnotification;
    @Autowired
    UserDao userDAO;
        @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {
            // If User is not Sales Manager or Administrator
            if(request.getSession().getAttribute("utype") == null ||
                    (request.getSession().getAttribute("utype").toString()).equals("1")) {
                return "home";
            }

            UserDao uDao = new UserDao();
            boolean flag = true; //uDao.updateForSM(521);
            //      request.getParameterValues("vip");
            String[] users = request.getParameterValues("vip[]");
            ArrayList<Integer> idlist = new ArrayList<Integer>();
            idlist = getIntegerList(users);

                for(int i=0; i<idlist.size(); i++){
                  flag = uDao.updateForSM(idlist.get(i));
             }

            sendNotification(idlist);

            return "smsettings";
    }

    private ArrayList<Integer> getIntegerList(String[] users){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<users.length;i++){
            res.add(Integer.parseInt(users[i]));
        }
        return res;
    }

    private void sendNotification(ArrayList<Integer> idlist){
        userDAO = new UserDao();
        emailnotification = new EmailNotification();
        for(Integer i: idlist){
            try {
                User u = userDAO.find(i);
                emailnotification.sendVIPStatusNotification(u.getEmail(),u.getName(),true);
            } catch (MyException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}