package com.mohdiop.database.daos;

import com.mohdiop.database.models.SCI;

import java.sql.SQLException;

public interface SCIDAO {
    Boolean initializeSCI(SCI sci) throws SQLException;

    Boolean updatePassword(String newPassword) throws SQLException;

    SCI getSCI() throws SQLException;
}
