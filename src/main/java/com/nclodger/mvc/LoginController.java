package com.nclodger.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/8/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", testDatabase("reshet.ukr@gmail.com","myhash"));

        return "index";
    }

    private boolean testDatabase(String _email, String _password)
    {
        // Context ctx = new InitialContext();
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();

        DataSource ds = (DataSource)ctx.lookup("jdbc/NCLodger");
        Connection con = ds.getConnection();//тут юзер и пасворд надо будет поменять

        //  Connection con = null;
        Statement st = con.createStatement();
       /* java.sql.ResultSet res = st.executeQuery("SELECT id FROM User WHERE " +
                "email= " + _email + " AND pswd= " + _password + ";");*/
            java.sql.ResultSet res = st.executeQuery("CREATE TABLE Users (ID NUMBER(11),email VARCHAR(100),pswd VARCHAR(100), username VARCHAR(100),register_confirmed NUMBER(6));");
        res.next();

            int exist = res.getInt(1);
        boolean answer = false;
        if(exist>0)
            answer = true;
        return answer;
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

}
