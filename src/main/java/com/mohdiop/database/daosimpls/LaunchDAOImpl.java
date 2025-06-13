package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.LaunchDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LaunchDAOImpl implements LaunchDAO {
    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Boolean isInitialized() throws SQLException {
        String query = "select * from launch";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public Boolean getFirstLaunchValue() throws SQLException {
        String query = "select * from launch";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean("isFirst");
    }

    @Override
    public void initialize() throws SQLException {
        String query = "insert into launch (isFirst) values (true)";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public void updateFirstValue(Boolean isFirstValue) throws SQLException {
        String query = "update launch set isFirst = false";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
    }
}