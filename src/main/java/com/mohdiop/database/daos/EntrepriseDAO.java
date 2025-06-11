package com.mohdiop.database.daos;

import com.mohdiop.database.models.Entreprise;

import java.sql.SQLException;

public interface EntrepriseDAO {
    String getNom() throws SQLException;

    Boolean updateName(String nom) throws SQLException;

    Boolean addEnterprise(String nom) throws SQLException;
}
