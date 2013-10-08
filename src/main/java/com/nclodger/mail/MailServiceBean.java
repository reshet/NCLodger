package com.nclodger.mail;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/8/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */

@Component("mail-service")
public class MailServiceBean {
    public String sayHello() {
        return "Hello World";
    }
}


