package livraria.infra;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;


public class EmailSender {
    public static void sendEmail(String to, String subject, String body) {

        Properties props = ConfigLoader.loadProperties();

        String from = props.getProperty("mail.user");
        String password = props.getProperty("mail.password");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}
