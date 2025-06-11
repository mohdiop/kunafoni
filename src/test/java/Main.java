import impltest.NotificationDAOImplTest;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        NotificationDAOImplTest test = new NotificationDAOImplTest();
        try {
            test.getAllNotifications();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
