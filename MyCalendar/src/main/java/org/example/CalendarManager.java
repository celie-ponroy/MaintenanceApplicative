package org.example;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CalendarManager {
    private final TreeSet<Event> events;

    public CalendarManager() {
        this.events = new TreeSet<>(Comparator.comparing(Event::getDateDebut));
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
        LocalDateTime fin1 = e1.getDateDebut().plusMinutes(e1.getDureeMinutes());
        LocalDateTime fin2 = e2.getDateDebut().plusMinutes(e2.getDureeMinutes());
        return e1.isInPeriod(e2.dateDebut,fin2) || e2.isInPeriod(e1.dateDebut,fin1);
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
}