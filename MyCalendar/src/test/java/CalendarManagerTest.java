import org.example.*;
import org.example.event.Event;
import org.example.event.EventPeriodique;
import org.example.event.EventRDVPersonnel;
import org.example.event.FabriqueEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarManagerTest {
    private CalendarManager calendar;
    private Event event1, event2, event3;
    private EventPeriodique periodicEvent;
    private Utilisateur alice,bob,charlie,sophie;

    @BeforeEach
    void setUp() {
        calendar = new CalendarManager();

        alice = new Utilisateur("Alice","motdepass");
        bob = new Utilisateur("Bob","1234");
        charlie = new Utilisateur("Charlie","Chacha");
        sophie = new Utilisateur("Sophie","1234");

        event1 = FabriqueEvent.getEventRDV("Réunion A", alice,
                LocalDateTime.of(2026, 3, 25, 10, 0), 60);
        event2 = FabriqueEvent.getEventRDV("Déjeuner", bob,
                LocalDateTime.of(2026, 3, 25, 12, 0), 90);
        event3 = FabriqueEvent.getEventRDV("Conférence", charlie,
                LocalDateTime.of(2026, 3, 26, 15, 0), 120);

        periodicEvent = FabriqueEvent.getEventPeriodique("Cours Yoga", sophie,
                LocalDateTime.of(2026, 3, 20, 18, 0), 60, 2);

        calendar.ajouterEvent(event1);
        calendar.ajouterEvent(event2);
        calendar.ajouterEvent(event3);
        calendar.ajouterEvent(periodicEvent);
    }

    @Test
    void testAjouterEvent() {
        Event newEvent = FabriqueEvent.getEventRDV("Dentiste", bob,
                LocalDateTime.of(2026, 3, 27, 9, 0), 30);

        calendar.ajouterEvent(newEvent);
        assertTrue(calendar.getEvents().contains(newEvent));
    }

    @Test
    void testSupprimerEvent() {
        assertTrue(calendar.supprimerEvent(event1));
        assertFalse(calendar.getEvents().contains(event1));
    }


    @Test
    void testEventsDansPeriode_AvecPeriodique() {
        LocalDateTime debut = LocalDateTime.of(2026, 3, 20, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2026, 3, 25, 23, 59);

        List<Event> events = calendar.eventsDansPeriode(debut, fin);

        assertTrue(events.contains(periodicEvent)); // Il doit apparaître plusieurs fois
    }

    @Test
    void testConflit_SansConflit() {
        assertFalse(calendar.conflit(event1, event2));
    }

    @Test
    void testConflit_AvecConflit() {
        Event overlappingEvent = FabriqueEvent.getEventReunion("Autre réunion", sophie,
                LocalDateTime.of(2026, 3, 25, 10, 30), 60, "Salle 1", "Alice, Bob");
        assertTrue(calendar.conflit(event1, overlappingEvent));
    }

    @Test
    void testConflit_AvecEvenementPeriodique() {
        Event conflictingEvent = FabriqueEvent.getEventRDV("Autre activité", alice,
                LocalDateTime.of(2026, 3, 22, 18, 30), 30);

        assertTrue(calendar.conflit(periodicEvent, conflictingEvent));
    }

    @Test
    public void testAjouterEvent_SansConflit() {
        calendar = new CalendarManager();
        // Création de deux événements non conflictuels
        EventRDVPersonnel event1 = FabriqueEvent.getEventRDV("Rendez-vous 1", bob, LocalDateTime.of(2025, 3, 25, 10, 0), 30);
        EventRDVPersonnel event2 = FabriqueEvent.getEventRDV("Rendez-vous 2", bob, LocalDateTime.of(2025, 3, 25, 11, 0), 30);

        // Ajout des événements
        boolean added1 = calendar.ajouterEvent(event1);
        boolean added2 = calendar.ajouterEvent(event2);

        // Vérification qu'ils ont été ajoutés avec succès
        assertTrue(added1, "Le premier événement aurait dû être ajouté.");
        assertTrue(added2, "Le second événement aurait dû être ajouté.");
    }

    @Test
    public void testAjouterEvent_AvecConflit() {
        calendar = new CalendarManager();
        // Création d'un événement
        EventRDVPersonnel event1 = FabriqueEvent.getEventRDV("Rendez-vous 1", bob, LocalDateTime.of(2025, 3, 25, 10, 0), 60);
        // Création d'un événement qui se chevauche avec le premier
        EventRDVPersonnel event2 = FabriqueEvent.getEventRDV("Rendez-vous 2", bob, LocalDateTime.of(2025, 3, 25, 10, 30), 30);

        // Ajout du premier événement
        boolean added1 = calendar.ajouterEvent(event1);
        // Tentative d'ajout d'un événement en conflit
        boolean added2 = calendar.ajouterEvent(event2);

        // Vérification que le second événement n'a pas été ajouté à cause du conflit
        assertTrue(added1, "Le premier événement aurait dû être ajouté.");
        assertFalse(added2, "Le second événement aurait dû être rejeté à cause du conflit.");
    }

    @Test
    public void testAjouterEvent_PeriodiqueAvecConflit() {
        calendar = new CalendarManager();
        // Création d'un événement périodique
        EventPeriodique event1 = FabriqueEvent.getEventPeriodique("Réunion périodique", bob, LocalDateTime.of(2025, 3, 25, 10, 0), 60, 1); // Fréquence de 1 jour
        // Création d'un événement qui se chevauche avec l'occurrence périodique
        EventRDVPersonnel event2 = FabriqueEvent.getEventRDV("Rendez-vous", bob, LocalDateTime.of(2025, 3, 25, 10, 30), 30);

        // Ajout du premier événement périodique
        boolean added1 = calendar.ajouterEvent(event1);
        // Tentative d'ajout d'un événement en conflit avec la première occurrence périodique
        boolean added2 = calendar.ajouterEvent(event2);

        // Vérification que le second événement n'a pas été ajouté
        assertTrue(added1, "L'événement périodique aurait dû être ajouté.");
        assertFalse(added2, "Le second événement aurait dû être rejeté à cause du conflit avec l'événement périodique.");
    }

    @Test
    public void testAjouterEvent_SansConflitAvecPeriodique() {
        calendar = new CalendarManager();
        // Création d'un événement périodique
        EventPeriodique event1 = FabriqueEvent.getEventPeriodique("Réunion périodique", bob, LocalDateTime.of(2025, 3, 25, 10, 0), 60, 1); // Fréquence de 1 jour
        // Création d'un événement qui ne chevauche pas les occurrences de l'événement périodique
        EventRDVPersonnel event2 = FabriqueEvent.getEventRDV("Rendez-vous", bob, LocalDateTime.of(2025, 3, 25, 12, 0), 30);

        // Ajout de l'événement périodique
        boolean added1 = calendar.ajouterEvent(event1);
        // Ajout d'un événement non-conflictuel avec l'événement périodique
        boolean added2 = calendar.ajouterEvent(event2);

        // Vérification qu'ils ont tous les deux été ajoutés
        assertTrue(added1, "L'événement périodique aurait dû être ajouté.");
        assertTrue(added2, "Le second événement aurait dû être ajouté.");
    }

}
