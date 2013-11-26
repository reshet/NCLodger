package com.nclodger.additional;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/29/13
 * Time: 8:03 PM
 */
@Component("MD5Value")
public class MD5Value {

    public MD5Value(){

    }

    public String getmd5value(String str){
        StringBuilder encodingresult = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                encodingresult.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Value.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodingresult.toString();
    }

}
