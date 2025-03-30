package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionnaireConnexion {
    private Utilisateur utilisateur = null;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    public GestionnaireConnexion() {
        utilisateurs.add(new Utilisateur("Roger","Chat"));
        utilisateurs.add(new Utilisateur("Pierre","KiRouhl"));
    }
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
    public void deconnexion(){
        utilisateur = null;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public List<Utilisateur> rechercherUtilisateurs(String filtre) {
        return utilisateurs.stream()
                .filter(u -> u.getNom().toLowerCase().contains(filtre.toLowerCase()))
                .collect(Collectors.toList());
    }


}
