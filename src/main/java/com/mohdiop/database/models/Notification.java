package com.mohdiop.database.models;

public class Notification {
    private String message;
    private String idExpediteur;
    public Notification(String message, String idExpediteur){
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
}
