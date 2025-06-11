package impltest;

import com.mohdiop.database.daosimpls.NotificationDAOImpl;
import com.mohdiop.database.models.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAOImplTest {

    private final NotificationDAOImpl test = new NotificationDAOImpl();

    public void addNewNotification() throws SQLException {
        if (test.addNewNotification(new Notification(
                "Bonjour vous, vous allez bien?",
                "kunafoni-292",
                2
        ))) {
            System.out.println("Notification ajouté avec succès!");
        } else {
            System.out.println("Notification non ajouté!");
        }
    }

    public void getNotificationById(int id) throws SQLException {
        Notification notification = test.getNotificationById(id);
        System.out.println("\n---------------------------------------------");
        System.out.printf("""
                ID          : %s
                Message     : %s
                Expéditeur  : %s
                Canal       : %s
                """, notification.getId(), notification.getMessage(), notification.getIdExpediteur(), notification.getIdCanal());
        System.out.println("---------------------------------------------");
    }

    public void getNotificationsByChannelId(int channelId) throws SQLException {
        ArrayList<Notification> notifications = test.getNotificationsByChannelId(channelId);
        System.out.println("---------------------------------------------");
        for(Notification notification: notifications){
            System.out.printf("""
                ID          : %s
                Message     : %s
                Expéditeur  : %s
                Canal       : %s
                """, notification.getId(), notification.getMessage(), notification.getIdExpediteur(), notification.getIdCanal());
            System.out.println("---------------------------------------------");
        }
    }

    public void getAllNotifications() throws SQLException {
        ArrayList<Notification> notifications = test.getAllNotifications();
        System.out.println("---------------------------------------------");
        for(Notification notification: notifications){
            System.out.printf("""
                ID          : %s
                Message     : %s
                Expéditeur  : %s
                Canal       : %s
                """, notification.getId(), notification.getMessage(), notification.getIdExpediteur(), notification.getIdCanal());
            System.out.println("---------------------------------------------");
        }
    }
}
