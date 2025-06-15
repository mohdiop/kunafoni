package com.mohdiop.views.sci;

import com.mohdiop.database.daosimpls.AbonnementDAOImpl;
import com.mohdiop.database.daosimpls.CanalDiffusionDAOImpl;
import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.daosimpls.EntrepriseDAOImpl;
import com.mohdiop.database.models.CanalDiffusion;
import com.mohdiop.database.models.Employe;
import com.mohdiop.utilities.PasswordGenerator;
import com.mohdiop.utilities.Utilities;
import com.mohdiop.views.AuthenticationView;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SCIMain {
    public static void firstLaunch() throws SQLException {
        EntrepriseDAOImpl enterpriseDAO = new EntrepriseDAOImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Interface SCI");
        if (!enterpriseDAO.isAlreadyCreatedEnterprise()) {
            createEnterprise();
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
        sciMainInterface();
    }

    public static void sciMainInterface() throws SQLException{
        EntrepriseDAOImpl entrepriseDAO = new EntrepriseDAOImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Bienvenue SCI - %s\n", entrepriseDAO.getNom());
        System.out.println("""
                1.) Envoyer un message
                2.) Ajouter un canal de diffusion
                3.) Ajouter un employé
                4.) Lister les canaux de diffusion
                5.) Lister les employés
                6.) Changer le nom de l'entreprise
                7.) Se déconnecter
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                addChannel(1);
                break;
            case 3:
                addEmployee(1);
                break;
            case 4:
                showChannels();
                break;
            case 5:
                //showEmployees();
                break;
            case 6:
                createEnterprise();
                break;
            case 7:
                AuthenticationView.authenticationScreen();
                break;
            default:
                sciMainInterface();
                break;
        }
    }

    private static void showChannels() throws SQLException{
        CanalDiffusionDAOImpl canalDiffusionDAO = new CanalDiffusionDAOImpl();
        EmployesDAOImpl employesDAO = new EmployesDAOImpl();
        ArrayList<CanalDiffusion> channels = canalDiffusionDAO.getAllCanal();
        int i = 0;
        System.out.println("\n---------------------------------------------");
        for (CanalDiffusion channel : channels){
            i++;
            int numberOfChannelEmployees = employesDAO.getEmployeesByDepartment(channel.getTitre()).size();
            System.out.printf("""
                    ID                : %d
                    Nom du canal      : %s
                    Nombre d'employés : %d""", i, channel.getTitre(), numberOfChannelEmployees);
            System.out.println("\n---------------------------------------------");
        }
        System.out.println("Choisissez le numéro d'un canal pour abonner des employés");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while(choice > channels.size() || choice == 0){
            System.out.println("Choix invalide!");
            System.out.println("Choisissez le numéro d'un canal pour abonner des employés :");
            choice = scanner.nextInt();
        }
        showChannel(channels.get(choice-1));
    }

    private static void showChannel(CanalDiffusion channel) throws SQLException {
        System.out.printf("Canal de diffusion %s", channel.getTitre());
        EmployesDAOImpl employesDAO = new EmployesDAOImpl();
        ArrayList<Employe> employees = employesDAO.getAllEmployees();
        AbonnementDAOImpl abonnementDAO = new AbonnementDAOImpl();
        System.out.println("Employés abonnés : ");
        for (Employe employe : employees) {
            if(abonnementDAO.isSubscribed(employe.getIdentifiant(), channel.getId())){
                System.out.printf("""
                        -- %s %s %s""", employe.getIdentifiant(), employe.getPrenom(), employe.getNom());
            }
        }
        System.out.println("Employés non abonnés : ");
        for (Employe employe : employees) {
            if(!abonnementDAO.isSubscribed(employe.getIdentifiant(), channel.getId())){
                System.out.printf("""
                        -- %s %s %s""", employe.getIdentifiant(), employe.getPrenom(), employe.getNom());
            }
        }
        System.out.println("""
                1.) Abonner un employé
                2.) Désabonner un employé""");
    }

    private static void createEnterprise() throws SQLException {
        EntrepriseDAOImpl enterpriseDAO = new EntrepriseDAOImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez fournir le nom de l'entreprise :");
        String enterpriseName = scanner.nextLine();
        Boolean isCreated = enterpriseDAO.addEnterprise(enterpriseName);
        while (!isCreated) {
            System.out.println("Erreur lors de l'ajout du nom de l'entreprise!");
            System.out.println("Veuillez fournir le nom de l'entreprise :");
            enterpriseName = scanner.nextLine();
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
            System.out.println("Veuillez fournir l'email de l'employé :");
            String email = scanner.nextLine();
            boolean isExistEmployee = employesDAO.getEmployeeByEmail(email) != null;
            while(isExistEmployee){
                System.out.println("Cet email est déjà attribué!\nVeuillez fournir un autre email:");
                email = scanner.nextLine();
                isExistEmployee = employesDAO.getEmployeeByEmail(email) != null;
            }
            System.out.println("Veuillez fournir le département de l'employé :");
            String departement = scanner.nextLine();
            System.out.println("Veuillez fournir le poste de l'employé :");
            String poste = scanner.nextLine();
            employees.add(new Employe("kunafoni-"+Utilities.generateIdentifiantNumber(),
                    BCrypt.hashpw(PasswordGenerator.generateRandomPassword(), BCrypt.gensalt()),
                    nom,
                    prenom,
                    email,
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
            try{
                areAddedEmployees.add(employesDAO.addNewEmployee(employee));
            } catch (SQLException e){
                areAddedEmployees.add(false);
            }
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
