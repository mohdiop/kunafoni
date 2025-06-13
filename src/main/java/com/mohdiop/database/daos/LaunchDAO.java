package com.mohdiop.database.daos;

import java.sql.SQLException;

public interface LaunchDAO {
    Boolean isInitialized() throws SQLException;
    Boolean getFirstLaunchValue() throws SQLException;
    void initialize() throws SQLException;
    void updateFirstValue(Boolean isFirstValue) throws SQLException;
}
