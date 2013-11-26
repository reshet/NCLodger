package com.nclodger.control.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Miredean
 * Date: 21.10.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */

abstract public class Action {
    static public ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml");
    abstract public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
