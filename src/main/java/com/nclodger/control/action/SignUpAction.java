package com.nclodger.control.action;

import com.nclodger.additional.MD5Value;
import com.nclodger.dao.ConfirmationEmail;
import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.mail.MailConfirm;
import com.nclodger.myexception.MyException;
//import org.springframework.context.annotation.Bean;

//import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:29
 */

public class SignUpAction implements Action {
    UserDao users;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        users = new UserDao();
        boolean bool;
        Users user;
        try {
            user = new Users(1,request.getParameter("email"),request.getParameter("password1"),request.getParameter("username"),0);
            bool = users.insert(user);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }

         //get hash
        String hash = new MD5Value().getmd5value(user.getEmail()+"."+user.getPswd());
        //add to DB
        putNewConfirmationEmailToDB(user.getId(),hash);
        //send mail
        new MailConfirm().sendMail(user.getEmail(),"http://37.139.6.189:8080/NCLodger/confirmation/"+hash);

        return "signup_succeed";
    }

     private boolean putNewConfirmationEmailToDB(int idUser,String hash) {
          ConfirmationEmailDAO ConEmail = new ConfirmationEmailDAO();
          return ConEmail.insert(new ConfirmationEmail(idUser,hash));
     }
}
