package com.mohdiop.database.daos;

import java.sql.SQLException;

public interface AbonnementDAO {
    Boolean addNewSubscription(String employeeId, int canalId) throws SQLException;

    Boolean deleteSubscription(String employeeId, int canalId) throws SQLException;

    Boolean isSubscribed(String employeeId, int canalId) throws SQLException;
}
