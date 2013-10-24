package com.nclodger.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 21.10.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */

public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
