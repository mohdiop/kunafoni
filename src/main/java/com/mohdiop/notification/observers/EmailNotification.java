package com.mohdiop.notification.observers;

import com.mohdiop.database.daosimpls.CanalDiffusionDAOImpl;
import com.mohdiop.notification.interfaces.NotificationObserver;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.SQLException;
import java.util.Properties;

public class EmailNotification implements NotificationObserver {

    private final CanalDiffusionDAOImpl canalDiffusionDAO = new CanalDiffusionDAOImpl();

    @Override
    public Boolean sendMessage(String from, String content, String to, String subject, int canalId) throws SQLException {
        String email = System.getenv().get("EMAIL");
        String password = System.getenv().get("PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String canalName = canalDiffusionDAO.getCanalById(canalId).getTitre();
        subject = from+" - "+canalName + " : " +subject;

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Email envoyé avec succès");
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
