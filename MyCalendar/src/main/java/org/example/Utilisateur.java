package org.example;

import java.util.Objects;

/**
 * Utilisateur de l'application
 */
public class Utilisateur {
    private String nom;//nom de l'utilisateur
    private String motDePasse;//mot de passe de l'utilisateur

    /**
     * Constructeur
     * @param nom
     * @param motDePasse
     */
    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    /**
     * Vérifie si le mot de passe en paramètre est corect
     * @param motDePasse mot de passe en question
     * @return
     */
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

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, motDePasse);
    }
}
