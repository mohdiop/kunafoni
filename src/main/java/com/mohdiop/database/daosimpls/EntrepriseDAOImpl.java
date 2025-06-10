package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.EntrepriseDAO;
import com.mohdiop.database.models.Entreprise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrepriseDAOImpl implements EntrepriseDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Entreprise getNom() throws SQLException {
        String query = "select * from entreprise";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new Entreprise (
                resultSet.getString("nom")
        );
    }

    @Override
    public Boolean updateName(String newName) throws SQLException {
        String query = "update entreprise set nom = "+newName;
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        return preparedStatement.execute();
    }
}
