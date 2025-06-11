import authtest.AuthenticationTest;
import impltest.NotificationDAOImplTest;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        AuthenticationTest test = new AuthenticationTest();
        try {
            test.login("admin", "admin", 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
