package com.nclodger.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/23/13
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */


@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext(",,/com/nclodger/dao/spring-test.xml")
public class UserDaoTest{

    @SpringBean("userdao")
    UserDaoInterface userDao;

    @Test
    @ExpectedDataSet
    public void testInsertUser() {

        //Users result = userDao.findByName("doe", "john");
        //assertPropertyLenientEquals("userName", "jdoe", result);
    }

    /*@Test
    public void testFindByMinimalAge() {
        List<Users> result = userDao.findByMinimalAge(18);
        assertPropertyLenientEquals("firstName", Arrays.asList("jack"), result);
    }*/
}
