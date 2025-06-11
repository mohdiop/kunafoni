package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.EntrepriseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrepriseDAOImpl implements EntrepriseDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public String getNom() throws SQLException {
        String query = "select * from entreprise";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("nom");
    }

    @Override
    public Boolean updateName(String newName) throws SQLException {
        String query = "update entreprise set nom = '" + newName + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean addEnterprise(String nom) throws SQLException {
        if (!isAlreadyCreatedEntreprise()) {
            String query = String.format("insert into entreprise (nom) values ('%s')", nom);
            PreparedStatement preparedStatement = pg.prepareStatement(query);
            preparedStatement.execute();
            return true;
        } else {
            return false;
        }
    }

    private Boolean isAlreadyCreatedEntreprise() throws SQLException {
        String query = "select * from entreprise";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
