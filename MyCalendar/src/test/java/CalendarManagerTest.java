import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarManagerTest {
    private CalendarManager calendar;
    private Event event1, event2, event3;
    private EventPeriodique periodicEvent;

    @BeforeEach
    void setUp() {
        calendar = new CalendarManager();

        event1 = FabriqueEvent.getEventRDV("Réunion A", "Alice",
                LocalDateTime.of(2026, 3, 25, 10, 0), 60);
        event2 = FabriqueEvent.getEventRDV("Déjeuner", "Bob",
                LocalDateTime.of(2026, 3, 25, 12, 0), 90);
        event3 = FabriqueEvent.getEventRDV("Conférence", "Charlie",
                LocalDateTime.of(2026, 3, 26, 15, 0), 120);

        periodicEvent = FabriqueEvent.getEventPeriodique("Cours Yoga", "Sophie",
                LocalDateTime.of(2026, 3, 20, 18, 0), 60, 2);

        calendar.ajouterEvent(event1);
        calendar.ajouterEvent(event2);
        calendar.ajouterEvent(event3);
        calendar.ajouterEvent(periodicEvent);
    }

    @Test
    void testAjouterEvent() {
        Event newEvent = FabriqueEvent.getEventRDV("Dentiste", "David",
                LocalDateTime.of(2026, 3, 27, 9, 0), 30);

        calendar.ajouterEvent(newEvent);
        assertTrue(calendar.events.contains(newEvent));
    }

    @Test
    void testSupprimerEvent() {
        assertTrue(calendar.supprimerEvent(event1));
        assertFalse(calendar.events.contains(event1));
    }

    @Test
    void testEventsDansPeriode() {
        LocalDateTime debut = LocalDateTime.of(2026, 3, 25, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2026, 3, 26, 23, 59);

        List<Event> events = calendar.eventsDansPeriode(debut, fin);

        assertEquals(3, events.size());
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
        assertTrue(events.contains(event3));
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
        Event overlappingEvent = FabriqueEvent.getEventReunion("Autre réunion", "Eve",
                LocalDateTime.of(2026, 3, 25, 10, 30), 60, "Salle 1", "Alice, Bob");
        assertTrue(calendar.conflit(event1, overlappingEvent));
    }

    @Test
    void testConflit_AvecEvenementPeriodique() {
        Event conflictingEvent = FabriqueEvent.getEventRDV("Autre activité", "Paul",
                LocalDateTime.of(2026, 3, 22, 18, 30), 30);

        assertTrue(calendar.conflit(periodicEvent, conflictingEvent));
    }

}
