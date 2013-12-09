package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;

import javax.mail.internet.HeaderTokenizer;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //когда будем выставлять на продакшн, следующую строку надо изменить
        String address = "http://localhost:8080/NCLodger/home.jsp";

        String basicUrl = "https://loginza.ru/api/widget?token_url="+address+"&lang=en&providers_set=vkontakte,facebook,twitter,openid,google,yandex";

        URL url = new URL(basicUrl);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;


        while ((inputLine = in.readLine()) != null)
           // System.out.println(inputLine);
        in.close();


        return "home";
    }
}
