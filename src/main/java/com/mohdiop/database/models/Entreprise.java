package com.mohdiop.database.models;

public class Entreprise {
    private String nom;
    public Entreprise(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
