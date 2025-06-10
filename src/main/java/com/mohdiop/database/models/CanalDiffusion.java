package com.mohdiop.database.models;

public class CanalDiffusion {
    private int id;
    private String titre;

    public CanalDiffusion(int id, String titre){
        this.id = id;
        this.titre = titre;
    }

    public CanalDiffusion(String titre){
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
