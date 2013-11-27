package com.nclodger.myexception;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 27.10.13
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class MyException extends Exception {
    static Logger log = Logger.getLogger(MyException.class.getName());
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
        PropertyConfigurator.configure("/var/www/log4j/log4j.properties");
        log.info(msg);
    }
}
