package com.nclodger.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/9/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
           public HomeController()
           {

           }
    //processes request to get homepage
    @RequestMapping({"/","/index"})

    public String showHomePage(Map<String,Object> model)
    {
        return "home";
    }
}
