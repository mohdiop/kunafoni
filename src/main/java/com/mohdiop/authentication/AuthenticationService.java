package com.mohdiop.authentication;

import java.sql.SQLException;

public class AuthenticationService{
    private Authentication user;
    private String identifiant;
    private String password;

    private static volatile AuthenticationService instance;

    public static synchronized AuthenticationService getInstance() {
        if(instance == null) {
            instance = new AuthenticationService(null,"","");
        }
        return instance;
    }

    private AuthenticationService(Authentication user, String identifiant, String password){
        this.user = user;
        this.identifiant = identifiant;
        this.password = password;
    }

    public Boolean authenticate() throws SQLException {
        return user.login(identifiant, password);
    }

    public AuthenticationService setUser(Authentication user) {
        this.user = user;
        return this;
    }

    public AuthenticationService setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
        return this;
    }

    public AuthenticationService setPassword(String password) {
        this.password = password;
        return this;
    }
}
