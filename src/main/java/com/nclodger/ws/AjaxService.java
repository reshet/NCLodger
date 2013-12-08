package com.nclodger.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/8/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class AjaxService {
    abstract public Serializable execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}