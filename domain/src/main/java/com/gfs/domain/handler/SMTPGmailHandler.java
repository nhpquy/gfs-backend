package com.gfs.domain.handler;

import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.utils.LoggerUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMTPGmailHandler implements Runnable {

    private String content;
    private String title;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private boolean isHTML;

    @Override
    public void run() {
        Session session = getSession();
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ApplicationProperties.getGmailProject()));
            for (String emailTo : to) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            }

            for (String emailCC : cc) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC));
            }

            for (String emailBCC : bcc) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailBCC));
            }

            message.setSubject(title);

            if (isHTML())
                message.setContent(content, "text/html; charset=utf-8");
            else
                message.setContent(content, "charset=utf-8");

            LoggerUtil.i(toString(), "Send email via SMTP gmail");
            Transport.send(message);

        } catch (Exception e) {
            LoggerUtil.exception(toString(), e, true);
        }
    }

    @Override
    public String toString() {
        return "Send email: " + title + " to " + to;
    }

    private Session getSession() {
        return generateSession(getGmailProperties());
    }

    private Session generateSession(Properties properties) {
        return Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ApplicationProperties.getGmailAccount(), ApplicationProperties.getGmailPwd());
            }
        });
    }

    private Properties getGmailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }
}
