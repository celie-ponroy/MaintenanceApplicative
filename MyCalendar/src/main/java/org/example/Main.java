package org.example;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

public class Main {
    CalendarManager calendar = new CalendarManager();
    Scanner scanner = new Scanner(System.in);
    GestionnaireConnexion gestionnaireConnexion = new GestionnaireConnexion();
    Menu menu = new Menu(scanner);
    InputMain input = new InputMain(scanner);
    boolean continuer = true;

    public static void main(String[] args) {
        new Main().Lancer();
    }

    /**
     * Lancement de l'application
     */
    public void Lancer() {
        while (continuer) {
            if (gestionnaireConnexion.getUtilisateur()== null) {
                afficherLogo();

                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");
                var choix = scanner.nextInt();
                var actions = new ArrayList<Runnable>();
                actions.add(this::menuConnexion);
                actions.add(this::menuInscription);
                actions.get(choix-1).run();

            }
            while (continuer && gestionnaireConnexion.getUtilisateur() != null) {
                menuPrincipal();
            }
        }
    }

    /**
     * Connexion d'un utilisateur
     */
    public void menuConnexion(){
        System.out.print("Nom d'utilisateur: ");
        scanner.nextLine();
        String nomUtilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();
        gestionnaireConnexion.connexion(nomUtilisateur,motDePasse);
    }

    public void menuInscription(){
        System.out.print("Nom d'utilisateur: ");
        scanner.nextLine();
        String nomUtilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");
        String secondMotDePasse = scanner.nextLine();
        gestionnaireConnexion.inscription(nomUtilisateur,motDePasse,secondMotDePasse);
    }

    private static void afficherListe(List<Event> evenements) {
        evenements.stream()
                .map(Event::description)
                .forEach(e -> System.out.println("- " + e));

        Optional.of(evenements.isEmpty())
                .filter(b -> b)
                .ifPresent(b -> System.out.println("Aucun événement trouvé pour cette période."));
    }

    /**
     * Menu principal de l'application une fois l'utilisateur connecté
     */
    private void menuPrincipal(){
        System.out.println("\nBonjour, " + gestionnaireConnexion.getUtilisateur());
        List<String> options = List.of(
                "Voir les événements",
                "Ajouter un rendez-vous perso",
                "Ajouter une réunion",
                "Ajouter un évènement périodique",
                "Ajouter une souscription",
                "Se déconnecter"
        );

        List<Runnable> actions = List.of(
                this::afficherEvenements,
                this::ajouterRdvPerso,
                this::ajouterReunion,
                this::ajouterEvenementPeriodique,
                this::ajouterSouscription,
                this::deconnexion
        );
        menu.afficherMenu("Menu Gestionnaire d'Événements", options, actions);

    }

    private void deconnexion() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        continuer = scanner.nextLine().trim().contains("O");
        gestionnaireConnexion.deconnexion();
    }
    private void afficherEvenements(){
        List<String> options = List.of(
                "Afficher TOUS les événements",
                "Afficher les événements d'un MOIS précis",
                "Afficher les événements d'une SEMAINE précise",
                "Afficher les événements d'un JOUR précis",
                "Retour"
        );

        List<Runnable> actions = List.of(
                this::afficherTousEvenements,
                this::afficherEvenementsMois,
                this::afficherEvenementsSemaine,
                this::afficherEvenementsJour,
                () -> {} // Ne rien faire pour "Retour"
        );
        menu.afficherMenu("Menu de visualisation d'Événements", options, actions);

    }

    private void afficherEvenementsJour() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
    }

    private void afficherEvenementsSemaine() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }

    private void afficherEvenementsMois() {

        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }

    private void afficherTousEvenements() {
        calendar.afficherEvenements();
    }

    //--------------------------------------Ajout des événements--------------------------------------
    private void ajouterRdvPerso(){
        // Ajout simplifié d'un RDV personnel
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        Event e = FabriqueEvent.getEventRDV(titre, gestionnaireConnexion.getUtilisateur(),
                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }
    private void ajouterReunion(){
        System.out.print("Titre de l'événement : ");
        String titre2 = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree2 = Integer.parseInt(scanner.nextLine());
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        String participants = gestionnaireConnexion.getUtilisateur().getNom();

        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui"))
        {
            System.out.print("Participants : " + participants);
            participants += ", " + scanner.nextLine();
        }
        Event e = FabriqueEvent.getEventReunion(titre2, gestionnaireConnexion.getUtilisateur(),
                LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2), duree2, lieu, participants);
        calendar.ajouterEvent(e);

        System.out.println("Événement ajouté.");
    }
    private void ajouterEvenementPeriodique(){
        String titre3 = input.lireTexte("Titre de l'événement :");
        int annee3 = input.lireEntier("Année (AAA) :");
        int moisRdv3 = input.lireEntier("Mois (1-12) :");
        int jourRdv3 = input.lireEntier("Jour (1-31) :");
        int heure3 = input.lireEntier("Heure début (0-23) : ");
        int minute3 =input.lireEntier("Minute début (0-59) : ");
        int frequence = input.lireEntier("Frequence (en jours) : ");

        Event e = FabriqueEvent.getEventPeriodique(titre3, gestionnaireConnexion.getUtilisateur(),
                LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3), 0, frequence);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }

    private void ajouterSouscription() {
        String titre = input.lireTexte("Titre de l'événement :");
        int annee = input.lireEntier("Année (AAA) :");
        int moisRdv = input.lireEntier("Mois (1-12) :");
        int jourRdv = input.lireEntier("Jour (1-31) :");
        int heure = input.lireEntier("Heure début (0-23) : ");
        int minute =input.lireEntier("Minute début (0-59) : ");
        int prix = input.lireEntier("Prix de la suscription : ");
        String entreprise = input.lireTexte("Entreprise chez qui on s'abonne");
        Event e = FabriqueEvent.getEventSouscription(titre,gestionnaireConnexion.getUtilisateur(), LocalDateTime.of(annee,moisRdv,jourRdv,heure,minute),0,prix,entreprise);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }

    /**
     * Affiche le logo de l'application
     */
    private static void afficherLogo() {

        System.out.println("████████████████████████████████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████████████████████████████████████████");
        System.out.println("▓▓▓▓▓▓▓▓▓▓████████████▓▓▓▓████████████▓▓▓▓▓▓██████████▓▓▓▓██▓▓██████████▓▓██▓▓████████████▓▓▓▓▓▓▓▓██████████▓▓▓▓▓▓");
        System.out.println("████████████████████████████▓▓▓▓▓▓██████░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██████▓▓▓▓▓▓▓▓████████▓▓██▓▓▓▓████████");
        System.out.println("████████████████████████████▓▓▓▓▓▓██████░░░░░░▒▒▓▓▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒▓▓▒▒██████▓▓▓▓▓▓██████████████▓▓██████████");
        System.out.println("████████████████████████████▓▓▓▓▓▓██████░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██████▓▓▓▓▓▓▓▓████████▓▓██▓▓██████████");
        System.out.println("████████████████████████████████████████░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██████████████████████████████████████");
        System.out.println("▓▓▓▓▓▓▓▓▓▓████████████▓▓▓▓██████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██▓▓██████████▓▓▓▓▓▓▓▓██████████▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓████████████▓▓▓▓██████████████░░░░░░                              ██▓▓██████████▓▓██▓▓▓▓██████████▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓██████████▓▓▓▓▓▓██████████████░░░░░░        ▒▒▒▒      ▒▒▒▒        ████████████████▓▓▓▓▓▓██████████▓▓▓▓▓▓");
        System.out.println("████████████████████████████████▓▓██████░░░░░░      ▒▒    ▒▒  ▒▒    ▒▒      ████████▓▓████████████████████████████");
        System.out.println("████████████████████████████▓▓██████████░░░░░░  ░░  ░░    ▒▒  ▒▒    ▒▒      ████▓▓▓▓████▓▓████████▓▓██████████████");
        System.out.println("██████████████████████████▓▓██▓▓████▓▓▓▓░░░░░░░░░░░░░░░░░░▒▒░░▒▒░░░░▒▒  ░░  ██████▓▓██▓▓▓▓████████████▓▓██████████");
        System.out.println("██████████████████████████▓▓▓▓▓▓██████▓▓░░░░░░░░░░  ░░░░▒▒░░  ░░▒▒▒▒░░░░    ▓▓▓▓██▓▓██████████████████▓▓██████████");
        System.out.println("██████████████████████████▓▓▓▓▓▓██████▓▓░░░░░░  ░░  ░░▒▒  ░░  ▒▒░░  ▒▒    ░░▓▓██▓▓▓▓██▓▓▓▓████████████████████████");
        System.out.println("██████████████████████████████▓▓██▓▓████░░░░░░░░  ░░▒▒░░  ░░  ▒▒░░  ▒▒  ░░░░██▓▓██▓▓▓▓██▓▓████████████████████████");
        System.out.println("████████████████████████████████████▓▓██░░░░░░      ▒▒        ▒▒    ▒▒      ██████████████▓▓██████████████████████");
        System.out.println("████████████████████████████████████▓▓██░░░░░░      ▒▒▒▒▒▒▒▒    ▒▒▒▒        ██████████████▓▓██████████████████████");
        System.out.println("▓▓██████▓▓██████████████████████████████░░░░░░                              ██▓▓████████████▓▓██▓▓████████████████");
        System.out.println("████████████████████████████████████████░░░░░░                              ██████████████████████████████████████");
        System.out.println("████████████████████████████▓▓▓▓▓▓██████░░░░░░                              ████████▓▓▓▓██████████████▓▓██████████");
        System.out.println("████████████████████████████▓▓▓▓▓▓██████░░░░░░          ▒▒▒▒▒▒▒▒▒▒          ████████▓▓▓▓██████████████▓▓██████████");
        System.out.println("██████████████████████████████▓▓▓▓██████░░░░░░                              ████████▓▓▓▓██████████████▓▓██████████");
        System.out.println("██████████████████████████████▓▓▓▓██████░░░░░░                              ████████▓▓▓▓██████████████▓▓██████████");
        System.out.println("▓▓▓▓▓▓▓▓▓▓████████████▓▓▓▓████████████▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████████████▓▓██▓▓▓▓████████████▓▓▓▓");System.out.println("██████████████████████████████████████▓▓██████████████▓▓██▓▓▓▓████████████████████████████▓▓▓▓██▓▓████████████████");

        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println(
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println(
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println(
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println(
                "                                                                                   __/ |");
        System.out.println(
                "                                                                                  |___/");
    }

}
