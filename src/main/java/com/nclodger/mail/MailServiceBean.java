package com.nclodger.mail;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/8/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */

@Component("mail-service")
public class MailServiceBean {
    public String sayHello() {
        return "Hello World";
    }
    private void send(String smtpHost, int smtpPort, String from, String to, String subject,
                            String content) throws AddressException, MessagingException {

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);

        Transport.send(msg);
    }
    public void sendRegisterMail(String user_mail){
        try {
            send("hostname", 25, "reshet.ukr@gmail.com",user_mail , "re: register at NCLodger service", "<h2>CONFIRMATION Letter</h2>");
        } catch (MessagingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}


