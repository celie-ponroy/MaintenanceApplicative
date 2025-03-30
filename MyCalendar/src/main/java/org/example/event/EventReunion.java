package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EventReunion extends Event {
    public final String lieu;
    private Set<Utilisateur> participants;

    public EventReunion(TitreEvent title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvent dureeMinutes, String lieu, Set<Utilisateur> participants) {
        super(EventType.REUNION, title, proprietaire, dateDebut, dureeMinutes);
        if(lieu == null || lieu.isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide");
        }
        this.lieu = lieu;
        if(participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Il doit y avoir au moins un participant");
        }
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants+" (id: "+ eventId+ ")";
    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }

    public Set<Utilisateur> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventReunion that = (EventReunion) o;
        return Objects.equals(lieu, that.lieu) && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lieu, participants);
    }
}
