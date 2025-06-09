package com.mohdiop.database.models;

import org.mindrot.jbcrypt.BCrypt;

public class Employe {
    private String identifiant;
    private String motDePasse;
    private String poste;
    private String departement;

    public Employe(String identifiant, String motDePasse, String poste, String departement){
        this.identifiant = identifiant;
        this.motDePasse = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
        this.poste = poste;
        this.departement = departement;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
