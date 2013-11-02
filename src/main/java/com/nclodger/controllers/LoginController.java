package com.nclodger.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/9/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController {
    public LoginController()
    {

    }

//    @RequestMapping(value = "/account/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }


    @RequestMapping(value = "/account/login", method = POST, params = "login")

    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password)
    {

        // do authentication
        return "redirect:home";
    }
}
