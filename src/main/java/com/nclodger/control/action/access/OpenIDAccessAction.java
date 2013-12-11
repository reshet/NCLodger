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


        if (request.getParameterMap().containsKey("token"))
        {
            String token = request.getParameter("token");
            URL url = new URL("http://loginza.ru/api/authinfo?token="+token);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            byte[] data = new byte[yc.getInputStream().available()];
            yc.getInputStream().read(data);
            in.close();
            //              out.println(new String(data));

            String userInfo = new String(data);
            //           out.println(userInfo);

            JSONObject json = new JSONObject(userInfo);

            JSONObject name =  json.getJSONObject("name");
            String firstName = name.getString("first_name");
            //                out.println(firstName);

            String email = json.getString("email");
            //               out.println(email);

            request.setAttribute("email",email);
            request.getSession().setAttribute("username",firstName);

            //положим пользователя в базу
            UserDao userDao = new UserDao();
            userDao.insert(email, firstName);





        User user;
            user = userDao.getUserObj2(email) ;
            request.getSession().setAttribute("userfull",user);

                // If user is SM then put id SM to session too
                if(user.getId_ut() == 2) {
            /*        String smEmail = request.getParameter("email");     */
                    SMDAO smDao = new SMDAO();
                    int idSm = smDao.getSmanagerId(email);
                    request.getSession().setAttribute("idSm",idSm);
                }

        }
        return "home";


    }
}
