package com.nclodger.mail;

import com.nclodger.webservices.Hotel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 11/21/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailNotification {


    public EmailNotification(){

    }

    public void sendMail(String to,String subject,String htmlTemplaneBody){
        final String username = "nclodger@gmail.com";
        final String password = "nclodger123456";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(htmlTemplaneBody , "text/html" );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //send when user registered
    public void sendConfirmation(String recipient,final String confirmURL){
        String htmlTemplaneBody = "<h2>Thanks for signing up! </h2>" +
                     "<p>Your account has been created. To activate your account, please use this link:</p> " +
                     confirmURL+
                     "<p>Respond to this message is not necessary.</p>"+
                     "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                     " If you are not registered on this site, please ignore this message.</p>";

        String subject = "Confirmation";
        sendMail(recipient,subject,htmlTemplaneBody);

    }
    //order user notification
    public void sendMailOrder(String recipient,Double final_price,Hotel h){
        String htmlTemplaneBody ="<h1>Thanks for booking a hotel!</h1>" +
                "<p>You was billed for:"+final_price+"</p>" +
                "<h2>Accomodation details:</h2>" +
                "Hotel "+h.getName()+"<br>" +
                "Address "+h.getAddress()+
                "<p>Respond to this message is not necessary.</p>";
        String subject = "Order Notification";
        sendMail(recipient,subject,htmlTemplaneBody);
    }

    // toBlocked  true, if user blocked
    // toBlocked false, if user unblocked
    public void sendBlockStatusNotification(String recipient, String username,boolean toBlocked){
        String unhappyhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>Unfortunately, you was blocked. Contact with SalesManager or Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";

        String happyhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>Your status was changed to unblocked. You can Contact with SalesManager or Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";
        String subject = "Change Block Status Notification";
        if (toBlocked){
            sendMail(recipient,subject,unhappyhtmlTemplaneBody);
        }
        else {
            sendMail(recipient,subject,happyhtmlTemplaneBody);
        }

    }
    // toVIP  true, if user blocked
    // toVIP false, if user unblocked
    public void sendVIPStatusNotification(String recipient, String username,boolean toVIP){
        String unVIPhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>Unfortunately, Your status was changed to simple user. Contact with SalesManager or Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";

        String VIPhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>Your status was changed to VIP. You can Contact with SalesManager or Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";
        String subject = "Change VIP Status Notification";
        if (toVIP){
            sendMail(recipient,subject,VIPhtmlTemplaneBody);
        }
        else {
            sendMail(recipient,subject,unVIPhtmlTemplaneBody);
        }
    }

    // toSM  true, if user granted to SalesManager
    // toSM false, if user revoke SalesManager
    public void sendSMStatusNotification(String recipient, String username,boolean toSM){
        String revokeSMhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>Unfortunately, You was revoked  salesmanager right. Contact with  Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";

        String granteSMhtmlTemplaneBody = "<h2>Dear," + username + " </h2>" +
                "<p>You was granted salesmanager right. You can contact with  Administator to get more information</p> " +
                "<p>Respond to this message is not necessary.</p>"+
                "<p>You received this message because your e-mail address was registered on the site www.nclodger.com." +
                " If you are not registered on this site, please ignore this message.</p>";
        String subject = "Change Salesmanager Status Notification";
        if (toSM){
            sendMail(recipient,subject,granteSMhtmlTemplaneBody);
        }
        else {
            sendMail(recipient,subject,revokeSMhtmlTemplaneBody);
        }
    }
}
