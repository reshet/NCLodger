package com.nclodger.mail;


import com.nclodger.webservices.HotelDTO;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/29/13
 * Time: 10:19 PM
 */


@Component("mailconfirmation")
public class MailConfirmation {


    public MailConfirmation(){

    }

    public void sendMail(String to,String confirmURL){
        final String username = "nclodger@gmail.com";
        final String password = "nclodger123456";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Confirmation");

            message.setContent("<h1>Thanks for signing up! \n" +
                    "Your account has been created, you can login after you have activated your account by pressing the url below.\n " +
                    confirmURL+
                    "\nif you did not register, please ignore this"+

                    "</h1>",
                    "text/html" );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMailOrder(String to,Double final_price,HotelDTO h){
        final String username = "nclodger@gmail.com";
        final String password = "nclodger123456";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Confirmation");

            message.setContent("<h1>Thanks for booking a hotel! \n" +
                    "</h1>" +
                    "<p>You was billed for:"+final_price+"</p>" +
                    "<h2>Accomodation details:</h2>" +
                    "HotelDTO "+h.getName()+"" +
                    "Address "+h.getAddress()+"",

                    "text/html" );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
