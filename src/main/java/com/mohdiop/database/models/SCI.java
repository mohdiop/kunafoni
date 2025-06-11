package com.mohdiop.database.models;

import org.mindrot.jbcrypt.BCrypt;

public class SCI {
    private final String identifiant;
    private String motDePasse;

    public SCI(String motDePasse){
        this.identifiant = "admin";
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
