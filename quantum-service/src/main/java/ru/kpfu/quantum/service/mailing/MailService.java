package ru.kpfu.quantum.service.mailing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.kpfu.quantum.spring.entities.PendingMail;
import ru.kpfu.quantum.spring.repository.PendingMailRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author sala
 */
public class MailService {
    private static final Log log = LogFactory.getLog(MailService.class);

    private String host;
    private String sender;
    private String username;
    private String password;

    public void setHost(String host) {
        this.host = host;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private PendingMailRepository pendingMailRepository;

    public void setPendingMailRepository(PendingMailRepository pendingMailRepository) {
        this.pendingMailRepository = pendingMailRepository;
    }

    public void init() {
        log.info("MailService initialized");
        log.info("Host: " + host);
        log.info("Sender: " + sender);
        log.info("Username: " + username);
        log.info("Password: " + password);
    }

    public void sendMail() {
        log.info("sendMail() called");
        final Page<PendingMail> mailToSend = pendingMailRepository.findAll(new PageRequest(0, 10));
        log.info(String.format("Found %d pending mails", mailToSend.getContent().size()));

        for(PendingMail pendingMail : mailToSend) {
            sendMail(pendingMail);
        }
    }

    private void sendMail(PendingMail pendingMail) {
        final String to = pendingMail.getReceiver();
        final String from = sender;
        final String host = this.host;
        //
        Properties properties = new Properties();
        // Setup mail server
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(properties, auth);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(pendingMail.getSubject());

            // Now set the actual message
            message.setText(pendingMail.getMessage());

            // Send message
            Transport.send(message);
            pendingMail.setSent(true);
            pendingMailRepository.save(pendingMail);
            log.info("Email was sent successfully");
        } catch (MessagingException mex) {
            log.error("An exception has occurred while sending mail", mex);
        }


    }

    private class SMTPAuthenticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = MailService.this.username;
            String password = MailService.this.password;
            return new PasswordAuthentication(username, password);
        }
    }

}
