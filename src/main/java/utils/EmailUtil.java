package utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtil {
    public static void sendMail(String host, String port, final String senderEmail,
                                final String password,
                                String recipientEmail,
                                String subject, Multipart message)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // creates a new session with an authenticator
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        // create a new email message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(senderEmail));
        InternetAddress[] toAddresses = { new InternetAddress(recipientEmail)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message);

//        Transport.send(msg);
        MailSender.queue(msg);
    }
}

class MailSender extends Thread {
    static {
        MailSender sender = new MailSender();
        sender.start();
    }

    static final List<Message> _queue = new ArrayList<>();

    public static void queue(Message mail) {
        // avoid deadlock
        synchronized (_queue) {
            _queue.add(mail);
            _queue.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (_queue) {
                    if (_queue.size() > 0) {
                        Message  mail = _queue.remove(0);
                        Transport.send(mail);
                    } else {
                        _queue.wait();
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
