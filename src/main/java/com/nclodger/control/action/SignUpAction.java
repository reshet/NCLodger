package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignUpAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean bool = true;
        // TODO: create dao object that will return true or false and sign it to "bool"
        // TODO: if true then dispatch signup_succeed.jsp else some kind of error page
        if(bool){
            return "signup_succeed";
        }
        else {
            return "Error";
        }
    }
}
