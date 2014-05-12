package com.netcracker.wind.mail;

import com.netcracker.wind.entities.User;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 * {@code MailSendler} is a class which help sending mails.
 *
 * @author Oksana
 * @author Sashko
 */
public class MailSendler {

    private static final String FROM = "wind@boreas.ml";
    private static final Logger LOGGER
            = Logger.getLogger(MailSendler.class.getName());

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
            if (true) {      //comment this block 
                return true; //when deploying to 
            }                //production server
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(FROM));
            for (User user : users) {
                message.addRecipients(Message.RecipientType.TO,
                        user.getEmail());
            }
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);
            LOGGER.info("Mail with subject \"" + subject
                    + "\" has been sent to " + users.size() + " user(s)");
            return true;
        } catch (MessagingException ex) {
            LOGGER.error(null, ex);
        }
        return false;
    }
}
