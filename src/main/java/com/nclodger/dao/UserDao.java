package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class UserDao implements UserDaoInterface
{
    public void insert(User _user)
    {



    }


    public void confirm_register()
    {
        ctx = new InitialContext();
        ds = (DataSource)ctx.lookup("jdbc/NCLodger");
        Connection con = ds.getConnection(username, password);//тут юзер и пасворд надо будет поменять
        PreparedStatement pstmt;
        con.setAutoCommit(false);

        pstmt = con.prepareStatement


    }

}
