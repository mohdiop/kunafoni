package impltest;

import com.mohdiop.database.daosimpls.SCIDAOImpl;
import com.mohdiop.database.models.SCI;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SCIDAOImplTest {

    private final SCIDAOImpl scidao = new SCIDAOImpl();

    /**
     * Cette fonction de test permet de tester l'ajout initial du compte SCI
     * @throws SQLException Lève une exception SQL en cas d'erreur
     */
    public void addSCI() throws SQLException {
        if(scidao.initializeSCI(new SCI(BCrypt.hashpw("admin", BCrypt.gensalt())))){
            System.out.println("SCI initialisé avec succès!");
        } else{
            System.out.println("SCI non initialisé!");
        }
    }

    /**
     * Cette fonction de test permet d'afficher le SCI
     * @throws SQLException Lève une exception SQL en cas d'erreur
     */
    public void getSCI() throws SQLException {
        SCI sci = scidao.getSCI();
        System.out.println("identifiant : " + sci.getIdentifiant() + "\n"
                + "mot de passe hashé : " + sci.getMotDePasse());
    }

    /**
     * Cette fonction de test permet de modifier le mot de passe du SCI
     * @throws SQLException Lève une exception SQL en cas d'erreur
     */
    public void updatePassword(String newPassword) throws SQLException{
        if(scidao.updatePassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()))){
            System.out.println("Mot de passe changé avec succès!");
        }else{
            System.out.println("Erreur! Mot de passe non changé.");
        }
    }
}
