package com.mohdiop.database;

import com.mohdiop.database.daosimpls.SCIDAOImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class PostgresDB {

    private static volatile PostgresDB instance;
    public Connection connection;

    private PostgresDB() {
        String url = "jdbc:postgresql://localhost/kunafoni";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "MOH");

        try {
            this.connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        initialize();
    }

    public static synchronized PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    private static ArrayList<String> getInitializationQueries() throws SQLException {
        ArrayList<String> queries = new ArrayList<>();
        queries.add("create table if not exists employes (identifiant varchar(12) not null primary key, nom varchar(255) not null, prenom varchar(255) not null, email varchar(255) not null unique, departement varchar(255) not null, motDePasse varchar(255) not null, poste varchar(50) not null);");
        queries.add("create table if not exists sci (identifiant varchar(5) primary key not null, motDePasse varchar(255) not null);");
        queries.add("create table if not exists canaux_diffusion (id serial primary key not null, titre varchar(50) not null);");
        queries.add("create table if not exists abonnements (idCanal int not null references canaux_diffusion(id), idEmploye varchar(12) not null references employes(identifiant), primary key (idCanal, idEmploye));");
        queries.add("create table if not exists notification (id serial primary key not null, message varchar(255) not null, identifiantExpediteur varchar(12) not null references employes(identifiant), canalId int not null references canaux_diffusion(id));");
        queries.add("create table if not exists entreprise (nom varchar(50) not null)");
        queries.add("create table if not exists launch (isFirst boolean not null)");
        return queries;
    }

    private void initialize() {
        try {
            ArrayList<PreparedStatement> preparedStatements = new ArrayList<>();
            ArrayList<String> queries = getInitializationQueries();
            for (String query : queries) {
                preparedStatements.add(this.connection.prepareStatement(query));
            }
            for (PreparedStatement preparedStatement : preparedStatements) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
