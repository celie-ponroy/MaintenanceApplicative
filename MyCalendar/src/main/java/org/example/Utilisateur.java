package org.example;

public class Utilisateur {
    private String nom;
    private String motDePasse;

    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }
    public boolean motDePasseCorrect(String motDePasse) {
        return false;
    }
}
