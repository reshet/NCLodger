package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;
import com.nclodger.dao.SMDAO;
import com.nclodger.dao.UserDao;
import com.nclodger.domain.User;
import com.nclodger.myexception.MyException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Boss
 * Date: 08.12.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class OpenIDAccessAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MyException, JSONException {

            String token = request.getParameter("token");
            URL url = new URL("http://loginza.ru/api/authinfo?token="+token);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            byte[] data = new byte[yc.getInputStream().available()];
            yc.getInputStream().read(data);
            in.close();

            String userInfo = new String(data);
            JSONObject json = new JSONObject(userInfo);
            String email = json.getString("email");
        //               out.println(email);
    /*
            JSONObject name =  json.getJSONObject("name");
            String firstName = name.getString("first_name");
            //                out.println(firstName);

    */
            String firstName = "Name";

            request.setAttribute("email",email);
            request.setAttribute("username",firstName);

            //положим пользователя в базу
            UserDao userDao = new UserDao();
            userDao.insert(email, firstName);


            User user;// = new User(email, firstName);
            user = userDao.getUserObj2(email) ;
            request.setAttribute("userfull",user);


                if (user != null && user.getConfirmRegister() == 1){
                    request.getSession().setAttribute("email",user.getEmail());
                    request.getSession().setAttribute("username",user.getName());
                    request.getSession().setAttribute("utype",user.getId_ut());
                    request.getSession().setAttribute("userfull",user);
                }

                if(user.getId_ut() == 2) {
                    SMDAO smDao = new SMDAO();
                    int idSm = smDao.getSmanagerId(email);
                    request.getSession().setAttribute("idSm",idSm);
                }


        return "home";


    }
}
