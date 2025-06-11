package com.mohdiop.authentication;

import java.sql.SQLException;

public interface Authentication {
    Boolean login(String identifiant, String password) throws SQLException;
}
