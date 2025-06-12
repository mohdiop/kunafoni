package com.mohdiop.views;

import com.mohdiop.authentication.AuthenticationService;
import com.mohdiop.authentication.EmployeeAuth;
import com.mohdiop.authentication.SCIAuth;
import com.mohdiop.views.employee.EmployeeMain;
import com.mohdiop.views.sci.SCIMain;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthenticationView {
    public static void authenticationScreen() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBienvenue sur Kunafoni votre application console de notifications\n");
        System.out.println("""
                1.) Connexion SCI
                2.) Connexion Employé
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                sciAuth();
                break;
            case 2:
                employeeAuth();
                break;
            default:
                break;
        }
    }

    private static void sciAuth() throws SQLException {
        AuthenticationService auth = AuthenticationService
                .getInstance()
                .setIdentifiant("admin")
                .setUser(new SCIAuth());

        Scanner sc = new Scanner(System.in);
        System.out.println("\nAuthentification SCI\n");
        System.out.println("Veuillez entrer votre mot de passe : ");
        String password = sc.nextLine();
        auth.setPassword(password);
        Boolean isAuthenticated = auth.authenticate();
        while (!isAuthenticated) {
            System.out.println("Mot de passe incorrect!");
            System.out.println("Veuillez entrer votre mot de passe : ");
            isAuthenticated = auth.setPassword(sc.nextLine()).authenticate();
        }
        SCIMain.sciMain();
    }

    private static void employeeAuth() throws SQLException {
        AuthenticationService auth = AuthenticationService
                .getInstance()
                .setUser(new EmployeeAuth());

        Scanner sc = new Scanner(System.in);
        System.out.println("\nAuthentification Employé\n");
        System.out.println("Veuillez entrer votre numéro d'identification :");
        String identifiant = "kunafoni-" + sc.nextLine();
        System.out.println("Veuillez entrer votre mot de passe : ");
        String password = sc.nextLine();
        auth.setIdentifiant(identifiant).setPassword(password);
        Boolean isAuthenticated = auth.authenticate();
        while (isAuthenticated == null || !isAuthenticated) {
            System.out.println("Vos informations de connexion sont incorrectes!");
            System.out.println("Veuillez entrer votre numéro d'identification :");
            identifiant = "kunafoni-" + sc.nextLine();
            System.out.println("Veuillez entrer votre mot de passe : ");
            isAuthenticated = auth.setIdentifiant(identifiant).setPassword(sc.nextLine()).authenticate();
        }
        EmployeeMain.employeeMain();
    }
}
