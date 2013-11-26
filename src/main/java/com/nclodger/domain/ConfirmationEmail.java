package com.nclodger.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/30/13
 * Time: 2:29 AM
 */
public class ConfirmationEmail {
    private int idUser;
    private String confirmHash;
    private Date confirmDate;

    public ConfirmationEmail(int idUser,String confirmHash)
    {
        this.idUser = idUser;
        this.confirmHash = confirmHash;
        this.confirmDate = new Date();
    }

    public ConfirmationEmail(int idUser,String confirmHash, Date confirmDate)
    {
        this.idUser = idUser;
        this.confirmHash = confirmHash;
        this.confirmDate = new Date(confirmDate.getTime());

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getConfirmHash() {
        return confirmHash;
    }

    public void setConfirmHash(String confirmHash) {
        this.confirmHash = confirmHash;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
}
