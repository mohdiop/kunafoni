package authtest;

import com.mohdiop.authentication.AuthenticationService;
import com.mohdiop.authentication.EmployeeAuth;
import com.mohdiop.authentication.SCIAuth;

import java.sql.SQLException;

public class AuthenticationTest {

    public void login(String identifiant, String password, int type) throws SQLException {
        AuthenticationService auth = AuthenticationService
                .getInstance()
                .setIdentifiant(identifiant)
                .setPassword(password);
        switch (type){
            case 1:
                auth.setUser(new SCIAuth());
                if(auth.authenticate()){
                    System.out.println("Authentification réussie!");
                }else{
                    System.out.println("Erreur d'authentification!");
                }
                break;
            case 2:
                auth.setUser(new EmployeeAuth());
                Boolean isAuthenticated = auth.authenticate();
                if(isAuthenticated == null){
                    System.out.println("Identifiant invalide!");
                } else if(isAuthenticated){
                    System.out.println("Authentification réussie!");
                } else {
                    System.out.println("Mot de passe invalide!");
                }
                break;
            default:
                break;
        }
    }
}
