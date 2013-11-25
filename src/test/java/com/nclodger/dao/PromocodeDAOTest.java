package com.nclodger.dao;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/25/13
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PromocodeDAOTest extends TestCase {
    @Test
    public void testisExist() throws IOException {
        try {
            PromoCodeDAO pcdao = new PromoCodeDAO();
            boolean isExist = pcdao.isExist("W0KN80RD1Y0-1");
            assertEquals(true,isExist);
            boolean isExist2 = pcdao.isExist("abcq");
            assertEquals(false,isExist2);

        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Test
    public void testisUsed() throws IOException {
        try {
            PromoCodeDAO pcdao = new PromoCodeDAO();
            boolean isUsed = pcdao.isUsed("W0KN80UR1E1-2");
            assertEquals(true,isUsed);
            boolean isUsed2 = pcdao.isUsed("abcq");
            assertEquals(false,isUsed2);

        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    public void testisExpired() throws IOException {
        try {
            PromoCodeDAO pcdao = new PromoCodeDAO();

            boolean isExpired = pcdao.isExpired("W0KN80UR1E1-2");
            assertEquals(false,isExpired);
            boolean isExpired2 = pcdao.isExpired("W0K");
            assertEquals(false,isExpired2);
            boolean isExpired3 = pcdao.isExpired("W0KN80RM1C2-2");
            assertEquals(true,isExpired3);




        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}
