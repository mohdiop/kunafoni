package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.SCIDAO;
import com.mohdiop.database.models.SCI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SCIDAOImpl implements SCIDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Boolean initializeSCI(SCI sci) throws SQLException {
        if (isAlreadyCreatedSCI()) {
            return true;
        } else {
            String query = String.format("insert into sci (identifiant, motDePasse) values (%s, %s)",
                    sci.getIdentifiant(), sci.getMotDePasse());
            PreparedStatement preparedStatement = pg.prepareStatement(query);
            return preparedStatement.execute();
        }
    }

    @Override
    public Boolean updatePassword(String newPassword) throws SQLException {
        String query = "update sci set motDePasse = " + newPassword;
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        return preparedStatement.execute();
    }

    @Override
    public SCI getSCI() throws SQLException {
        String query = "select * from sci";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return new SCI(
                resultSet.getString("motDePasse")
        );
    }

    private Boolean isAlreadyCreatedSCI() throws SQLException {
        String query = "select * from sci";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.first();
    }
}
