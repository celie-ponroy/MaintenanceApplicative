package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Classe permettant d'afficher un menu et d'exécuter l'action correspondante
 */
public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Affiche un menu et exécute l'action correspondante
     * @param titre
     * @param options
     * @param actions
     */

    public void afficherMenu(String titre, List<String> options, List<Runnable> actions) {
        System.out.println("\n=== " + titre + " ===");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i));
        }
        System.out.print("Votre choix : ");

        try {
            int choix = Integer.parseInt(scanner.nextLine()) - 1;
            actions.get(choix).run();
        } catch (Exception e) {
            System.out.println("Choix invalide !");
        }
    }
}
