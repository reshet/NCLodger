package com.nclodger.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/8/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {
    @RequestMapping({"/","/home"})
    public String showHomePage(Map<String, Object> model) {
      return "home";
    }

}
