package com.mohdiop.database.models;

import org.mindrot.jbcrypt.BCrypt;

public class Employe {
    private String identifiant;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String poste;
    private String departement;

    public Employe(String identifiant, String motDePasse, String nom, String prenom, String poste, String departement){
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
