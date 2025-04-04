package org.example;

import org.example.event.Event;
import org.example.event.FabriqueEvent;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Permets de lancer l'application
 */
public class Main {
    CalendarManager calendar = new CalendarManager(); //gestion de evenements
    Scanner scanner = new Scanner(System.in);
    GestionnaireConnexion gestionnaireConnexion = new GestionnaireConnexion();//gestion des connexions
    Menu menu = new Menu(scanner);//gestion du menu de l'application
    InputMain input = new InputMain(scanner); // permets de centraliser les inputs
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

    /**
     * Permets de s'inscrire dans les utilisateurs
     */
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

    /**
     * Affiche la liste d'évènements de l'utilisateur
     * @param evenements
     */
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
                "Supprimer un évènement",
                "Se déconnecter"
        );

        List<Runnable> actions = List.of(
                this::afficherEvenements,
                this::ajouterRdvPerso,
                this::ajouterReunion,
                this::ajouterEvenementPeriodique,
                this::ajouterSouscription,
                this::supprimerEvent,
                this::deconnexion
        );
        menu.afficherMenu("Menu Gestionnaire d'Événements", options, actions);

    }

    /**
     * Permets à un utilisateur de se déconnecter
     */
    private void deconnexion() {
        continuer = input.lireConfirmation("Déconnexion ! Voulez-vous continuer ?");
        gestionnaireConnexion.deconnexion();
    }
    //--------------------------------------Affichage des événements--------------------------------------

    /**
     * Menu d'affichage des évènements
     */
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
    /**
     * affiche les évènements d'un jour précis
     */
    private void afficherEvenementsJour() {
        int anneeJour =input.lireEntier("Entrez l'année (AAAA) : ");
        int moisJour = input.lireEntier("Entrez le mois (1-12) : ");
        int jour =  input.lireEntier("Entrez le jour (1-31) : ");

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
    }
    /**
     * affiche les évènements d'une semaine précise
     */
    private void afficherEvenementsSemaine() {
        int anneeSemaine = input.lireEntier("Entrez l'année (AAAA) : ");
        int semaine = input.lireEntier("Entrez le numéro de semaine (1-52) : ");

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }

    /**
     * affiche les évènements d'un mois précis
     */
    private void afficherEvenementsMois() {
        int anneeMois = input.lireEntier("Entrez l'année (AAAA) : ");
        int mois = input.lireEntier("Entrez le mois (1-12) : ");
        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }

    /**
     * affiche tous les évènements
     */
    private void afficherTousEvenements() {
        calendar.afficherEvenements();
    }

    //--------------------------------------Ajout des événements--------------------------------------
    /**
     * Permets d'ajouter un évènement Rendez vous perso
     */
    private void ajouterRdvPerso(){
        // Ajout simplifié d'un RDV personnel
        String titre = input.lireTexte("Titre de l'événement : ");
        LocalDateTime debutRdvPerso = input.saisirDateHeure();
        int duree = input.lireEntier("Durée (en minutes) : ");
        Event e = FabriqueEvent.getEventRDV(titre, gestionnaireConnexion.getUtilisateur(),
                debutRdvPerso, duree);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }
    /**
     * Permets d'ajouter un évènement Réunion
     */
    private void ajouterReunion(){
        String titre2 = input.lireTexte("Titre de l'événement : ");
        LocalDateTime debutReunion = input.saisirDateHeure();
        int duree = input.lireEntier("Durée (en minutes) : ");
        String lieu = input.lireTexte("Lieu :");

        Set<Utilisateur> participants  = input.ajouterParticipants();

        Event e = FabriqueEvent.getEventReunion(titre2, gestionnaireConnexion.getUtilisateur(),
                debutReunion, duree, lieu, participants);
        calendar.ajouterEvent(e);

        System.out.println("Événement ajouté.");
    }
    /**
     * Permets d'ajouter un évènement periodique
     */
    private void ajouterEvenementPeriodique(){
        String titre3 = input.lireTexte("Titre de l'événement :");
        LocalDateTime debutEvenementPeriodique = input.saisirDateHeure();
        int frequence = input.lireEntier("Frequence (en jours) : ");

        Event e = FabriqueEvent.getEventPeriodique(titre3, gestionnaireConnexion.getUtilisateur(),
                debutEvenementPeriodique, 0, frequence);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }

    /**
     * Permets d'ajouter un évènement "Souscription"
     */
    private void ajouterSouscription() {
        String titre = input.lireTexte("Titre de l'événement :");
        LocalDateTime debutSouscription = input.saisirDateHeure();
        int prix = input.lireEntier("Prix de la suscription : ");
        String entreprise = input.lireTexte("Entreprise chez qui on s'abonne");
        Event e = FabriqueEvent.getEventSouscription(titre,gestionnaireConnexion.getUtilisateur(),debutSouscription,0,prix,entreprise);
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }

    /**
     * supprime un event avec son ID
     */
    public void supprimerEvent(){
        String ID = input.lireTexte("ID de l'évènement : ");
        calendar.supprimerEventParId(ID);
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
