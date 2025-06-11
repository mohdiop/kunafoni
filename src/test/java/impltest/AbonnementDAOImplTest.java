package impltest;

import com.mohdiop.database.daosimpls.AbonnementDAOImpl;

import java.sql.SQLException;

public class AbonnementDAOImplTest {

    private final AbonnementDAOImpl test = new AbonnementDAOImpl();

    public void addNewAbonnement(String employeeId, int canalId) throws SQLException {
        if (test.addNewSubscription(employeeId, canalId)) {
            System.out.println("Abonnement ajouté avec succès!");
        } else {
            System.out.println("Abonnement non ajouté!");
        }
    }

    public void isSubscribed(String employeeId, int canalId) throws SQLException {
        if (test.isSubscribed(employeeId, canalId)) {
            System.out.printf("L'employé %s est abonné au canal %d!", employeeId, canalId);
        } else {
            System.out.println("L'employé n'est pas abonné!");
        }
    }

    public void deleteSubscription(String employeeId, int canalId) throws SQLException {
        if(test.deleteSubscription(employeeId, canalId)) {
            System.out.printf("L'employé %s a été désabonné du canal %d avec succès!", employeeId, canalId);
        } else {
            System.out.printf("L'employé %s n'a pas pu être désabonné du canal %d!", employeeId, canalId);
        }
    }
}
