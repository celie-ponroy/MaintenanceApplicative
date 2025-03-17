import org.example.CalendarManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarManagerTest {
    CalendarManager calendarManager;
    @BeforeEach
    public void setUp() {
        calendarManager = new CalendarManager();
    }
    @Test
    public void testAjouterEvent() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", "RDV", "Roger", LocalDateTime.now(), 30, "", "", 0);
        assertEquals(1, calendarManager.events.size());
        assertEquals("RDV", calendarManager.events.get(0).title);
    }
    @Test
    public void testAjouterEventTypeIncorrect() {
        calendarManager.ajouterEvent(null, "RDV", "Roger", LocalDateTime.now(), 30, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventTitleIncorrect() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", null, "Roger", LocalDateTime.now(), 30, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventProprietaireIncorrect() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", "RDV", null, LocalDateTime.now(), 30, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventDateDebutIncorrect() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", "RDV", "Roger", null, 30, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventDureeMinutesIncorrectNeg() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", "RDV", "Roger", LocalDateTime.now(), -1, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventDureeMinutesIncorrect0() {
        calendarManager.ajouterEvent("RDV_PERSONNEL", "RDV", "Roger", LocalDateTime.now(), 0, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventFrequenceJoursIncorrect() {
        calendarManager.ajouterEvent("PERIODIQUE", "RDV", "Roger", LocalDateTime.now(), 30, "", "", -1);
        assertEquals(0, calendarManager.events.size());
    }
    @Test
    public void testAjouterEventFrequenceJoursIncorrect2() {
        calendarManager.ajouterEvent("PERIODIQUE", "RDV", "Roger", LocalDateTime.now(), 30, "", "", 0);
        assertEquals(0, calendarManager.events.size());
    }
}
