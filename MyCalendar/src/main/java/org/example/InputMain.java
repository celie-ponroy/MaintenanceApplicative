package org.example;

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
}
