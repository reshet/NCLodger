package com.nclodger.control.action.access;

import com.nclodger.control.action.Action;
import com.nclodger.myexception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */
public class SignOutAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("utype");
        request.getSession().removeAttribute("promo_code");
        return "home";
    }
}
