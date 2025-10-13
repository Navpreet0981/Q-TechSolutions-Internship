package Email_Client_Simulation;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailClientSimulation {

    public static void sendEmail(String receiver, String subject, String messageText) {
        // Replace with your actual details
        final String senderEmail = "your_email@gmail.com";
        final String senderPassword = "your_password_or_app_specific_password";

        // Gmail SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Email sent successfully to " + receiver);

        } catch (MessagingException e) {
            System.out.println("Failed to send email.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Sample usage
        String receiver = "receiver@example.com";
        String subject = "Test Email";
        String messageText = "Hello, this is a simulated email sent using JavaMail API!";
        sendEmail(receiver, subject, messageText);
    }
}
