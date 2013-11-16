package com.nclodger.control.action;

import com.nclodger.additional.MD5Value;
import com.nclodger.dao.ConfirmationEmail;
import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.dao.ConfirmationEmailDAO;
import com.nclodger.mail.MailConfirmation;
import com.nclodger.myexception.MyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml");
        UserDao users = (UserDao) ctx.getBean("userDAO");

        boolean bool;
        Users user;
        try {
            user = new Users(1,request.getParameter("email"),request.getParameter("password"),request.getParameter("username"),0);
            bool = users.insert(user);
            Users user_stored = users.getUserObj(user.getEmail(),user.getPswd());
            ConfirmationEmailDAO ConEmail =(ConfirmationEmailDAO) ctx.getBean("conemailDAO");

            //get hash
            MD5Value MD = (MD5Value) ctx.getBean("md5value");
            String hash = MD.getmd5value(user.getEmail()+"."+user.getPswd());
            ConEmail.insert(new ConfirmationEmail(user_stored.getId(),hash));
            //send mail
            MailConfirmation mailconfirm =(MailConfirmation) ctx.getBean("mailconfirmation");
            mailconfirm.sendMail(user.getEmail(),"http://"+request.getLocalAddr()+":8080/NCLodger/confirmation?param="+hash);
        } catch (MyException ex) {
            request.setAttribute("error_message",ex.getMessage());
            return "exception";
        }

        return "signup_succeed";
    }

}
