package impltest;

import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.models.Employe;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.mohdiop.utilities.Utilities.generateIdentifiantNumber;

public class EmployesDAOImplTest {

    private final EmployesDAOImpl test = new EmployesDAOImpl();

    public void addNewEmployee() throws SQLException {
        if (test.addNewEmployee(new Employe(
                "kunafoni-" + generateIdentifiantNumber(),
                BCrypt.hashpw("123", BCrypt.gensalt()),
                "Nom",
                "Prénom",
                "Poste",
                "Département"
        ))) {
            System.out.println("Employé ajouté avec succès!");
        } else {
            System.out.println("Employé non ajouté");
        }
    }

    public void getEmployeesByDepartment(String department) throws SQLException {
        ArrayList<Employe> employees = test.getEmployeesByDepartment(department);
        System.out.println("\n---------------------------------------------");
        for (Employe employee : employees) {
            System.out.printf("""
                            Identifiant  : %s
                            Mot de passe : %s
                            Nom          : %s
                            Prénom       : %s
                            Département  : %s
                            Poste        : %s""",
                    employee.getIdentifiant(),
                    employee.getNom(),
                    employee.getMotDePasse(),
                    employee.getPrenom(),
                    employee.getDepartement(),
                    employee.getPoste());
            System.out.println("\n---------------------------------------------");
        }
    }

    public void getEmployeeById(String identifiant) throws SQLException {
        Employe employee = test.getEmployeeById(identifiant);
        if (employee == null) {
            System.out.println("L'employé n'existe pas!");
        } else {
            System.out.println("\n---------------------------------------------");
            System.out.printf("""
                            Identifiant  : %s
                            Mot de passe : %s
                            Nom          : %s
                            Prénom       : %s
                            Département  : %s
                            Poste        : %s""",
                    employee.getIdentifiant(),
                    employee.getNom(),
                    employee.getMotDePasse(),
                    employee.getPrenom(),
                    employee.getDepartement(),
                    employee.getPoste());
            System.out.println("---------------------------------------------");
        }
    }

    public void getAllEmployees() throws SQLException {
        ArrayList<Employe> employees = test.getAllEmployees();
        System.out.println("\n---------------------------------------------");
        for (Employe employee : employees) {
            System.out.printf("""
                            Identifiant  : %s
                            Mot de passe : %s
                            Nom          : %s
                            Prénom       : %s
                            Département  : %s
                            Poste        : %s""",
                    employee.getIdentifiant(),
                    employee.getNom(),
                    employee.getMotDePasse(),
                    employee.getPrenom(),
                    employee.getDepartement(),
                    employee.getPoste());
            System.out.println("---------------------------------------------");
        }
    }

    public void updatePassword(String employeeId, String newPassword) throws SQLException {
        if (test.updatePassword(employeeId, BCrypt.hashpw(newPassword, BCrypt.gensalt()))) {
            System.out.println("Mot de passe changé avec succès!");
        } else {
            System.out.println("Mot de passe non changé!");
        }
    }

    public void updateDepartment(String employeeId, String newDepartment) throws SQLException{
        if(test.updateDepartment(employeeId, newDepartment)){
            System.out.println("Département changé avec succès!");
        } else {
            System.out.println("Département non changé");
        }
    }

    public void updatePosition(String employeeId, String newPosition) throws SQLException{
        if(test.updatePosition(employeeId, newPosition)){
            System.out.println("Poste changé avec succès!");
        } else {
            System.out.println("Poste non changé");
        }
    }

    public void deleteEmployeeById(String employeeId) throws SQLException {
        if(test.deleteEmployeeById(employeeId)){
            System.out.println("Employé supprimé avec succès!");
        } else {
            System.out.println("Employé non supprimé!");
        }
    }
}
