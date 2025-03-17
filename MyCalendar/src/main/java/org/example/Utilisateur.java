package org.example;

public class Utilisateur {
    private String nom;
    private String motDePasse;

    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }
    public boolean motDePasseCorrect(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        Utilisateur u = (Utilisateur) o;
        return u.nom.equals(nom) && u.motDePasse.equals(motDePasse);
    }
}
