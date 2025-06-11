package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.CanalDiffusionDAO;
import com.mohdiop.database.models.CanalDiffusion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CanalDiffusionDAOImpl implements CanalDiffusionDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public CanalDiffusion getCanalById(int id) throws SQLException {
        String query = "select * from canaux_diffusion where id = " + id;
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new CanalDiffusion(
                resultSet.getInt("id"),
                resultSet.getString("titre")
        );
    }

    @Override
    public ArrayList<CanalDiffusion> getAllCanal() throws SQLException {
        String query = "select * from canaux_diffusion";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<CanalDiffusion> channels = new ArrayList<>();
        while (resultSet.next()) {
            channels.add(new CanalDiffusion(
                    resultSet.getInt("id"),
                    resultSet.getString("titre")
            ));
        }
        return channels;
    }

    @Override
    public Boolean createNewCanal(CanalDiffusion newCanalDiffusion) throws SQLException {
        String query = String.format("insert into canaux_diffusion (titre) values ('%s')", newCanalDiffusion.getTitre());
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean deleteCanalById(int id) throws SQLException {
        String query = "delete from canaux_diffusion where id = " + id;
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }
}
