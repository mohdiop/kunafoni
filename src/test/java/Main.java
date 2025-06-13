import com.mohdiop.database.daosimpls.LaunchDAOImpl;
import com.mohdiop.database.daosimpls.SCIDAOImpl;
import com.mohdiop.database.models.SCI;
import com.mohdiop.views.AuthenticationView;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            initializeSCI();
            initializeLaunch();
            AuthenticationView.authenticationScreen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void initializeSCI() throws SQLException {
        SCIDAOImpl scidao = new SCIDAOImpl();
        if (!scidao.isAlreadyCreatedSCI()) {
            scidao.initializeSCI(new SCI(BCrypt.hashpw("admin", BCrypt.gensalt())));
        }
    }

    public static void initializeLaunch() throws SQLException {
        LaunchDAOImpl launchDAO = new LaunchDAOImpl();
        if (!launchDAO.isInitialized()) {
            launchDAO.initialize();
        }
    }
}
