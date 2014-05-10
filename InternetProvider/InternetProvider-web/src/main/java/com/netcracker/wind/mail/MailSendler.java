/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.mail;

import com.netcracker.wind.entities.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Oksana and Sashko MailSendler is a class which help sending mails
 */
public class MailSendler {

    private static final String FROM = "wind@boreas.ml";

    @Resource(lookup = "mail/MyMail")
    private Session mailSession;

    /**
     *
     * @param users list of destination email addresses
     * @param subject string for the subject of sending email
     * @param body is actually a message
     * @return true when massage has been sent; - false - when
     * MessagingException or other exceptions have happened
     */
    public boolean sendEmail(List<User> users, String subject, String body) {

        try {

            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(FROM));

            for(User user:users){

                message.setRecipients(Message.RecipientType.TO,user.getEmail());
            }
            message.setSubject(subject);

            message.setSentDate(new Date());
            message.setText(body);

            Transport.send(message);
            
            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(MailSendler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
