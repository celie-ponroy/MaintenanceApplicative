package org.example;

import org.example.event.Event;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * CalendarManager gère les évènements
 */
public class CalendarManager {
    private final TreeSet<Event> events;

    /**
     * Constructeur
     */
    public CalendarManager() {

        this.events = new TreeSet<>(Comparator
                .comparing(Event::getDateDebut)
                .thenComparing(Event::getDureeMinutesInt)
                .thenComparing(Event::getEventId));

    }

    /**
     * Permets d'ajouter un évènement si il ne cause aucun conflits
     * @param event l'évènement à ajouter
     * @return si l'évènement a été ajouté
     */
    public boolean ajouterEvent(Event event) {
        if (event == null || events.stream().anyMatch(e -> conflit(e, event))) {
            return false;
        }

        return events.add(event);
    }

    /**
     * Renvoi les évènements qui sont dans une période donée
     * @param debut date de début de la période
     * @param fin date de fin de la période
     * @return la liste d'évènements
     */
    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.stream()
                .filter(e -> e.isInPeriod(debut, fin))
                .collect(Collectors.toList());
    }

    /**
     * Vérifie si deux événements sont en conflit
     * @param e1 le premier evenement
     * @param e2 le second evenement
     * @return vrai si les deux événements sont en conflit
     */
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime debut1 = e1.getDateDebut();
        LocalDateTime fin1 = debut1.plusMinutes(e1.getDureeMinutes().minutes());
        LocalDateTime debut2 = e2.getDateDebut();
        LocalDateTime fin2 = debut2.plusMinutes(e2.getDureeMinutes().minutes());

        return debut1.isBefore(fin2) && debut2.isBefore(fin1);
    }

    /**
     * Affiche tous les évènements
     */
    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    /**
     * Supprime un évènement
     * @param event l'évenement à supprimer
     * @return si il a bien été supprimé
     */
    public boolean supprimerEvent(Event event) {
        return events.remove(event);
    }
    /**
     * permets de supprimer un event par l'id
     * @param eventId l'id de l'évènement à supprimer
     * @return si il a bien été supprimé
     */
    public boolean supprimerEventParId(String eventId) {
        return events.removeIf(e -> e.getEventId().equals(eventId));
    }


    /**
     * getEvents()
     * @return la liste des évènements
     */
    public TreeSet<Event> getEvents() {
        return events;
    }


}