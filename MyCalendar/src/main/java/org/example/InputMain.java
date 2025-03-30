package org.example;

import java.time.LocalDateTime;
import java.util.*;

//s'occupe de récuperrer les inputs pour le main
public class InputMain {
    private Scanner scanner;

    InputMain(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Lis un string après avoir affiché le message
     * @param message le message affiché
     * @return le String donné par l'utilisateur
     */
    public String lireTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    /**
     * Lis un entier après avoir affiché le message
     * @param message le message affiché
     * @return le entier donné par l'utilisateur
     */
    public int lireEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }
    /**
     * Lis une contirmation (O/N)  après avoir affiché le message
     * @param message le message affiché
     * @return la réponse de l'utilisateur
     */
    public boolean lireConfirmation(String message) {
        System.out.print(message + " (O/N) : ");
        return scanner.nextLine().trim().equalsIgnoreCase("O");
    }

    /**
     * Lis une date (année, mois,jour,heure,minutes)
     * @return LocalDateTime corespondant
     */
    public LocalDateTime saisirDateHeure() {
        int annee = this.lireEntier("Année (AAAA) :");
        int mois = this.lireEntier("Mois (1-12) :");
        int jour = this.lireEntier("Jour (1-31) :");
        int heure = this.lireEntier("Heure début (0-23) :");
        int minute = this.lireEntier("Minute début (0-59) :");
        return LocalDateTime.of(annee, mois, jour, heure, minute);
    }

    /**
     * Permets d'ajouter des participants de la base de donnée à un Set
     * @return le Set d'utilisateurs
     */
    public Set<Utilisateur> ajouterParticipants() {
        Set<Utilisateur> participants = new HashSet<>();
        boolean ajouterPlus = true;

        while (ajouterPlus) {
            System.out.print("Rechercher un utilisateur (nom) : ");
            String recherche = scanner.nextLine();
            List<Utilisateur> resultats = new GestionnaireConnexion().rechercherUtilisateurs(recherche);

            resultats.forEach(u -> System.out.println((resultats.indexOf(u) + 1) + " - " + u.getNom()));

            Optional.of(resultats)
                    .filter(list -> !list.isEmpty())
                    .map(list -> {
                        System.out.println("Sélectionnez un utilisateur (ID) : ");
                        return scanner.nextInt();
                    })
                    .filter(choix -> choix > 0 && choix <= resultats.size())
                    .map(choix -> resultats.get(choix - 1))
                    .ifPresent(participants::add);

            scanner.nextLine(); // Pour éviter le bug de saut de ligne
            ajouterPlus = scanner.nextLine().equalsIgnoreCase("oui");
        }
        return participants;
    }


}
