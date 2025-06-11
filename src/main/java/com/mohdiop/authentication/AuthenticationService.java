package com.mohdiop.authentication;

import java.sql.SQLException;

public class AuthenticationService{
    private Authentication user;
    private String identifiant;
    private String password;

    public AuthenticationService(Authentication user, String identifiant, String password){
        this.user = user;
        this.identifiant = identifiant;
        this.password = password;
    }

    public Boolean authenticate() throws SQLException {
        return user.login(identifiant, password);
    }

    public void setUser(Authentication user) {
        this.user = user;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
