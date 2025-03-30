package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

//s'occupe de récuperrer les inputs pour le main
public class InputMain {
    private Scanner scanner;
    InputMain(Scanner scanner) {
        this.scanner = scanner;
    }
    public String lireTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

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

    public boolean lireConfirmation(String message) {
        System.out.print(message + " (O/N) : ");
        return scanner.nextLine().trim().equalsIgnoreCase("O");
    }
    public LocalDateTime saisirDateHeure() {
        int annee = this.lireEntier("Année (AAAA) :");
        int mois = this.lireEntier("Mois (1-12) :");
        int jour = this.lireEntier("Jour (1-31) :");
        int heure = this.lireEntier("Heure début (0-23) :");
        int minute = this.lireEntier("Minute début (0-59) :");
        return LocalDateTime.of(annee, mois, jour, heure, minute);
    }

}
