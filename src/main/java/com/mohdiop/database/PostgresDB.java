package com.mohdiop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class PostgresDB {

    public Connection connection;

    private static volatile PostgresDB instance;

    public PostgresDB() {
        String url = "jdbc:postgresql://localhost/kunafoni";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "MOH");

        try {
            this.connection = DriverManager.getConnection(url, props);
            System.out.println("Connected Successfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        initialize();
    }

    public void initialize() {
        try {
            ArrayList<PreparedStatement> preparedStatements = new ArrayList<>();
            ArrayList<String> sqls = getInitializationQueries();
            for (String sql : sqls) {
                preparedStatements.add(this.connection.prepareStatement(sql));
            }
            for (PreparedStatement preparedStatement : preparedStatements) {
                preparedStatement.execute();
            }
            System.out.println("Base de données initialisée avec succès!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static ArrayList<String> getInitializationQueries() {
        ArrayList<String> sqls = new ArrayList<>();
        sqls.add("create table if not exists employes (identifiant varchar(12) not null primary key, motDePasse varchar(50) not null, poste varchar(50) not null);");
        sqls.add("create table if not exists sci (identifiant varchar(5) primary key not null, motDePasse varchar(50) not null);");
        sqls.add("create table if not exists canaux_diffusion (id int primary key not null, titre varchar(50) not null);");
        sqls.add("create table if not exists abonnements (id int primary key not null, idCanal int not null references canaux_diffusion(id), idEmploye varchar(12) not null references employes(identifiant));");
        sqls.add("create table if not exists notification (id int primary key not null, message varchar(255) not null, identifiantExpediteur varchar(12) not null references employes(identifiant));");
        return sqls;
    }
}
