package com.nclodger.controllers;

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


    @RequestMapping(value = "/account/login", method = RequestMethod.POST, params = "login")

    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password)
    {

        // do authentication
        return "redirect:home";
    }
}
