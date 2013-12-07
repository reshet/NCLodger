package com.nclodger.publicdao;

import com.nclodger.additional.AccommodationTotalValue;
import com.nclodger.additional.HotelTotalOrder;
import com.nclodger.domain.SManager;
import com.nclodger.logic.HotelCommissionDTO;
import com.nclodger.myexception.MyException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 30.10.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface SMDaoInterface {
    public boolean insert(SManager smanager) throws MyException;
    public boolean insert(int id_user) throws MyException;
    public boolean delete(SManager smanager) throws MyException;
    public boolean delete(int id_user) throws MyException;
    public boolean getSManager(String email, String password) throws MyException;
    public SManager getSManager(int id) throws MyException;
    public int getSmanagerId(String email) throws MyException;
    public boolean setCommAndDiscounts(int id_sm, double commission, double vip_discount, double user_discount) throws MyException;
    public List<HotelTotalOrder> sortHotelbyPopular() throws MyException;
    public SManager getCurrentCommAndDiscounts(final String email) throws MyException;
    public List<HotelTotalOrder> sortHotelbyPopularWithTimeFrame(final String start, final String end) throws MyException;
    public List<AccommodationTotalValue> sortAccommodationbyValuable() throws MyException;
    public List<AccommodationTotalValue> sortAccommodationbyValuableWithTimeFrame(final String start, final String end) throws MyException;
    public List<HotelCommissionDTO> getHotelCommissions(int hotel_exp_id) throws MyException;
    public Boolean isOccupied(Integer id_sm, Integer id_hotel) throws MyException;
}