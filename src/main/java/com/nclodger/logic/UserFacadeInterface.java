package com.nclodger.logic;

import com.nclodger.domain.Order;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 12/4/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserFacadeInterface {
    void saveOrder(Order ord);
    void calculateFinalPrice(Order ord);
}
