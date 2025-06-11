package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.AbonnementDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonnementDAOImpl implements AbonnementDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Boolean addNewSubscription(String employeeId, int canalId) throws SQLException {
        if(!isSubscribed(employeeId, canalId)){
            String query = String.format("insert into abonnements (idEmploye, idCanal) values ('%s', '%s')",
                    employeeId, canalId);
            PreparedStatement preparedStatement = pg.prepareStatement(query);
            preparedStatement.execute();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteSubscription(String employeeId, int canalId) throws SQLException {
        String query = String.format("delete from abonnements where idEmploye = '%s' and idCanal = '%s'",
                employeeId, canalId);
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean isSubscribed(String employeeId, int canalId) throws SQLException {
        String query = String.format("select * from abonnements where idEmploye = '%s' and idCanal = '%s'",
                employeeId, canalId);
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
