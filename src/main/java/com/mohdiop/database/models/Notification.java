package com.mohdiop.database.models;

import java.lang.invoke.StringConcatFactory;

public class Notification {
    private int id;
    private String message;
    private String idExpediteur;

    public Notification(int id, String message, String idExpediteur) {
        this.id = id;
        this.message = message;
        this.idExpediteur = idExpediteur;
    }

    public Notification(String message, String idExpediteur) {
        this.message = message;
        this.idExpediteur = idExpediteur;
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
}
