package org.example;

import org.example.event.Event;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CalendarManager {
    private final TreeSet<Event> events;

    public CalendarManager() {

        this.events = new TreeSet<>(Comparator
                .comparing(Event::getDateDebut)
                .thenComparing(Event::getDureeMinutesInt)
                .thenComparing(Event::getEventId));

    }

    public boolean ajouterEvent(Event event) {
        if (event == null || events.stream().anyMatch(e -> conflit(e, event))) {
            return false;
        }

        return events.add(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.stream()
                .filter(e -> e.isInPeriod(debut, fin))
                .collect(Collectors.toList());
    }

    /**
     * Vérifie si deux événements sont en conflit
     * @param e1
     * @param e2
     * @return
     */
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime debut1 = e1.getDateDebut();
        LocalDateTime fin1 = debut1.plusMinutes(e1.getDureeMinutes().minutes());
        LocalDateTime debut2 = e2.getDateDebut();
        LocalDateTime fin2 = debut2.plusMinutes(e2.getDureeMinutes().minutes());

        return debut1.isBefore(fin2) && debut2.isBefore(fin1);
    }


    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public boolean supprimerEvent(Event event) {
        return events.remove(event);
    }

    public TreeSet<Event> getEvents() {
        return events;
    }

    /**
     * permets de supprimer un event par l'id
     * @param eventId
     * @return
     */
    public boolean supprimerEventParId(String eventId) {
        return events.removeIf(e -> e.getEventId().equals(eventId));
    }

}