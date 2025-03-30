package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GestionnaireConnexion gère les utilisateurs et l'utilisateur connecté.
 */
public class GestionnaireConnexion {
    private Utilisateur utilisateur = null;//utilisateur connecté
    private List<Utilisateur> utilisateurs = new ArrayList<>();//liste des utilisateurs

    /**
     * Constructeur
     */
    public GestionnaireConnexion() {
        utilisateurs.add(new Utilisateur("Roger","Chat"));
        utilisateurs.add(new Utilisateur("Pierre","KiRouhl"));
    }

    /**
     * Permets à un utilisateur de se connecter
     * @param nomUtilisateur nom de l'utilisateur
     * @param motDePasse mot de passe
     */
    public void connexion(String nomUtilisateur,String motDePasse){
        Utilisateur utilisateurTmp = new Utilisateur(nomUtilisateur,motDePasse);
        //on regarde si l'utilisateur est déjà dans la liste
        if(utilisateurs.contains(utilisateurTmp))
            utilisateur = utilisateurTmp;
        else
            deconnexion();
    }
    /**
     * Inscription d'un nouvel utilisateur
     */
    public void inscription(String nomUtilisateur,String motDePasse,String secondMotDePasse){
        utilisateur = new Utilisateur(nomUtilisateur,motDePasse);
        for (Utilisateur utilisateurTmp : utilisateurs) {
            if (utilisateurTmp.getNom().equals(utilisateur.getNom())) {
                System.out.println("Cet utilisateur existe déjà...");
                deconnexion();
                return;
            }
        }
        if (secondMotDePasse.equals(motDePasse)) {
            utilisateurs.add(utilisateur);
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
            deconnexion();
        }
    }

    /**
     * deconnexion de l'utilisateur courrant
     */
    public void deconnexion(){
        utilisateur = null;
    }

    /**
     * Renvoi les utilisateurs qui continnent dans leurs une chaine de charactère
     * @param filtre la chaine de charactère
     * @return la liste d'utilisateurs en question
     */
    public List<Utilisateur> rechercherUtilisateurs(String filtre) {
        return utilisateurs.stream()
                .filter(u -> u.getNom().toLowerCase().contains(filtre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
