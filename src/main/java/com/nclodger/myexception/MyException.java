package com.nclodger.myexception;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 27.10.13
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class MyException extends Exception {
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
    }
}
