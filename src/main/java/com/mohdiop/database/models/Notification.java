package com.mohdiop.database.models;

import java.lang.invoke.StringConcatFactory;

public class Notification {
    private int id;
    private String message;
    private String idExpediteur;
    private int idCanal;

    public Notification(int id, String message, String idExpediteur, int idCanal) {
        this.id = id;
        this.message = message;
        this.idExpediteur = idExpediteur;
        this.idCanal = idCanal;
    }

    public Notification(String message, String idExpediteur, int idCanal) {
        this.message = message;
        this.idExpediteur = idExpediteur;
        this.idCanal = idCanal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(String idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }
}
