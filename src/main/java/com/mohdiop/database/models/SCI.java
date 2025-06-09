package com.mohdiop.database.models;

import org.mindrot.jbcrypt.BCrypt;

public class SCI {
    private String identifiant;
    private String motDePasse;

    public SCI(String motDePasse){
        this.identifiant = "admin";
        this.motDePasse = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
