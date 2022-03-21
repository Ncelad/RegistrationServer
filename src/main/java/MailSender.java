import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

public class MailSender {

    public static void Send(User user) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gorba4evkirill@gmail.com", "gkscydofxpqkhqtd");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("gorba4evkirill@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(user.Login));
        message.setSubject("Mail Subject");

        String msg = "You have been successfully registered!";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
