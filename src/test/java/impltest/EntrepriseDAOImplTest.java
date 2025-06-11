package impltest;

import com.mohdiop.database.daosimpls.EntrepriseDAOImpl;

import java.sql.SQLException;

public class EntrepriseDAOImplTest {

    private final EntrepriseDAOImpl test = new EntrepriseDAOImpl();

    public void addEnterprise(String nom) throws SQLException {
        if(test.addEnterprise(nom)) {
            System.out.println("Entreprise ajouté avec succès!");
        } else {
            System.out.println("Entreprise non ajouté!");
        }
    }

    public void getEnterpriseName() throws SQLException {
        System.out.println(test.getNom());
    }

    public void updateEnterpriseName(String newName) throws SQLException {
        if(test.updateName(newName)){
            System.out.println("Le nom a été changé avec succès!");
        } else {
            System.out.println("Le nom n'a pas été changé");
        }
    }
}
