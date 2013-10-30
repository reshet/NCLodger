package com.nclodger.mail;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/29/13
 * Time: 10:19 PM
 */


public class MailConfirm {
    public MailConfirm (){

    }
    //to -recipient of email;
    //maybe should send user's login and password
    public void sendMail(String to,String confirmURL){

        //String from = "noreplay@nclodger.com";
        String from = "noreply@gmail.com";


        String host = "127.0.0.1";
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");
            // Send the actual HTML message
            message.setContent("<h1>Thanks for signing up! \n" +
                    "Your account has been created, you can login after you have activated your account by pressing the url below.\n " +
                    confirmURL+
                    "\nif you did not register, please ignore this"+

                    "</h1>",
                    "text/html" );
            // Send message
            Transport.send(message);
//            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
