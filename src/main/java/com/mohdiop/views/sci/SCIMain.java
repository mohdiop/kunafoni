package com.mohdiop.views.sci;

import com.mohdiop.database.daosimpls.CanalDiffusionDAOImpl;
import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.daosimpls.EntrepriseDAOImpl;
import com.mohdiop.database.models.CanalDiffusion;
import com.mohdiop.database.models.Employe;
import com.mohdiop.utilities.PasswordGenerator;
import com.mohdiop.utilities.Utilities;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SCIMain {
    public static void firstLaunch() throws SQLException {
        EntrepriseDAOImpl enterpriseDAO = new EntrepriseDAOImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Interface SCI");
        if (!enterpriseDAO.isAlreadyCreatedEnterprise()) {
            createEnterprise(enterpriseDAO, scanner);
        }
        System.out.println("""
                Voulez-vous ajouter un canal de diffusion?
                1.) OUI
                2.) NON
                """);
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            addChannel(choice);
        }
        System.out.println("""
                Voulez-vous ajouter un employé?
                1.) OUI
                2.) NON
                """);
        choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            addEmployee(choice);
        }
    }

    private static void createEnterprise(EntrepriseDAOImpl enterpriseDAO, Scanner scanner) throws SQLException {
        System.out.println("Veuillez fournir le nom de l'entreprise :");
        String enterpriseName = scanner.next();
        Boolean isCreated = enterpriseDAO.addEnterprise(enterpriseName);
        while (!isCreated) {
            System.out.println("Erreur lors de l'ajout du nom de l'entreprise!");
            System.out.println("Veuillez fournir le nom de l'entreprise :");
            enterpriseName = scanner.next();
            isCreated = enterpriseDAO.addEnterprise(enterpriseName);
        }
        System.out.printf("""
                Le nom de votre entreprise est configuré avec succès!
                Nom de l'entreprise : %s
                """, enterpriseName);
    }

    private static void addChannel(int choice) throws SQLException {
        System.out.println("Ajout de chaîne");
        CanalDiffusionDAOImpl canalDiffusionDAO = new CanalDiffusionDAOImpl();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> channels = new ArrayList<>();
        while (choice == 1) {
            System.out.println("Veuillez fournir le nom de la chaîne : ");
            channels.add(scanner.nextLine());
            System.out.println("""
                    Voulez-vous en ajouter un autre?
                    1.) OUI
                    2.) NON
                    """);
            choice = Integer.parseInt(scanner.nextLine());
        }
        for (String channel: channels) {
            canalDiffusionDAO.createNewCanal(new CanalDiffusion(channel));
        }
        switch (channels.size()) {
            case 0:
                break;
            case 1:
                System.out.println("Canal ajouté avec succès!");
                break;
            default:
                System.out.println("Canaux ajoutés avec succès!");
                break;
        }
    }

    private static void addEmployee(int choice) throws SQLException {
        System.out.println("Ajout des employés");
        EmployesDAOImpl employesDAO = new EmployesDAOImpl();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employe> employees = new ArrayList<>();
        while(choice == 1){
            System.out.println("Veuillez fournir le nom de l'employé :");
            String nom = scanner.nextLine();
            System.out.println("Veuillez fournir le prénom de l'employé :");
            String prenom = scanner.nextLine();
            System.out.println("Veuillez fournir le département de l'employé :");
            String departement = scanner.nextLine();
            System.out.println("Veuillez fournir le poste de l'employé :");
            String poste = scanner.nextLine();
            employees.add(new Employe("kunafoni-"+Utilities.generateIdentifiantNumber(),
                    BCrypt.hashpw(PasswordGenerator.generateRandomPassword(), BCrypt.gensalt()),
                    nom,
                    prenom,
                    poste,
                    departement));
            System.out.println("""
                Voulez-vous ajouter un autre employé?
                1.) OUI
                2.) NON
                """);
            choice = Integer.parseInt(scanner.nextLine());
        }
        ArrayList<Boolean> areAddedEmployees = new ArrayList<>();
        for (Employe employee : employees) {
            areAddedEmployees.add(employesDAO.addNewEmployee(employee));
        }
        for (Boolean isAddedEmployee : areAddedEmployees){
            if(!isAddedEmployee){
                Employe employee = employees.get(areAddedEmployees.indexOf(isAddedEmployee));
                System.out.printf("""
                        L'employé %s %s n'a pas pu être ajouté!
                        """, employee.getPrenom(), employee.getNom());
            }
        }
        switch (employees.size()) {
            case 0:
                break;
            case 1:
                System.out.println("Employé ajouté avec succès!");
                break;
            default:
                System.out.println("Employés ajoutés avec succès!");
                break;
        }
    }
}
