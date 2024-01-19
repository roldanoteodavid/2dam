package domain.servicios.impl;

import common.Constants;
import config.Configuration;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;


/**
 *
 * @author oscar
 */
@Log4j2
public class MandarMail {

    private final Configuration config;

    @Inject
    public MandarMail(Configuration config) {
        this.config = config;
    }

    public void generateAndSendEmail(String to, String msg, String subject) throws MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;

        // Step1

        mailServerProperties = System.getProperties();
        mailServerProperties.put(Constants.MAIL_SMTP_PORT, Integer.parseInt(Constants.NUMBER));
        mailServerProperties.put(Constants.MAIL_SMTP_AUTH, Constants.TRUE);
        mailServerProperties.put(Constants.MAIL_SMTP_SSL_TRUST, Constants.SMTP_GMAIL_COM);
        mailServerProperties.put(Constants.MAIL_SMTP_STARTTLS_ENABLE, Constants.TRUE);

        // Step2

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);
        String emailBody = msg;
        generateMailMessage.setContent(emailBody, Constants.TEXT_HTML);


        // Step3

        Transport transport = getMailSession.getTransport(Constants.SMTP);

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect(Constants.SMTP_GMAIL_COM,
                config.getProperty(Constants.EMAIL),
                config.getProperty(Constants.PASSWORDEMAIL));

        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
