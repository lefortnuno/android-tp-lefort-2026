package com.example.iao_app;

public class Plat {
    private String nom;
    private String prix;
    private String description;
    private int imageResourceId;

    public Plat(String nom, String prix, String description, int imageResourceId) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getNom() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}