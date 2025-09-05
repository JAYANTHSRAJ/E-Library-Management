package in.ineuron.util;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String toEmail, String subject, String body) {
        // Sender email and password (use a dedicated email for the system)
        final String fromEmail = "jayanthgaja9666@gmail.com";
        final String password = "ovra nprg asvf pdfu";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // For Gmail
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
