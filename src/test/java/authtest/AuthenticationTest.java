package authtest;

import com.mohdiop.authentication.AuthenticationService;
import com.mohdiop.authentication.EmployeeAuth;
import com.mohdiop.authentication.SCIAuth;

import java.sql.SQLException;

public class AuthenticationTest {

    public void login(String identifiant, String password, int type) throws SQLException {
        AuthenticationService auth = new AuthenticationService(new SCIAuth(), identifiant, password);
        switch (type){
            case 1:
                if(auth.authenticate()){
                    System.out.println("Authentification réussie!");
                }else{
                    System.out.println("Erreur d'authentification!");
                }
            case 2:
                auth.setUser(new EmployeeAuth());
                if(auth.authenticate()){
                    System.out.println("Authentification réussie!");
                }else{
                    System.out.println("Erreur d'authentification!");
                }
        }
    }
}
