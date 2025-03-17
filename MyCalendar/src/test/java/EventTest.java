import org.example.Event;
import org.example.EventPeriodique;
import org.example.EventReunion;
import org.example.FabriqueEvent;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventTest {
    //------------Rdv perso----------------
    @Test
    public void RdvpersoClassic(){
        Event e = FabriqueEvent.getEventRDV("RDV", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60);
        assertEquals("RDV_PERSONNEL", e.type);
        assertEquals("RDV", e.title);
        assertEquals("moi", e.proprietaire);
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.dateDebut);
        assertEquals(60, e.dureeMinutes);
    }
    @Test
    public void RdvpersoNullProprio(){
        Event e = FabriqueEvent.getEventRDV("RDV", null, LocalDateTime.of(2021, 1, 1, 12, 0), 60);
        assertNull(e);
    }
    @Test
    public void RdvpersoNullDate(){
        Event e = FabriqueEvent.getEventRDV("RDV", "moi", null, 60);
        assertNull(e);
    }
    @Test
    public void RdvpersoDatePassée(){
        Event e = FabriqueEvent.getEventRDV("RDV", "moi", LocalDateTime.of(2020, 1, 1, 12, 0), 60);
        assertNull(e);
    }

    //------------RDV Periodique----------------
    @Test
    public void RDVPeriodiqueClassic(){
        EventPeriodique e = (EventPeriodique) FabriqueEvent.getEventPeriodique("RDV", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60, 7);
        assertEquals("PERIODIQUE", e.type);
        assertEquals("RDV", e.title);
        assertEquals("moi", e.proprietaire);
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.dateDebut);
        assertEquals(60, e.dureeMinutes);
        assertEquals(7, e.frequenceJours);
    }
    @Test
    public void RDVPeriodiqueNullProprio(){
        Event e = FabriqueEvent.getEventPeriodique("RDV", null, LocalDateTime.of(2021, 1, 1, 12, 0), 60, 7);
        assertNull(e);
    }
    @Test
    public void RDVPeriodiqueNullDate(){
        Event e = FabriqueEvent.getEventPeriodique("RDV", "moi", null, 60, 7);
        assertNull(e);
    }
    @Test
    public void RDVPeriodiqueDatePassée(){
        Event e = FabriqueEvent.getEventPeriodique("RDV", "moi", LocalDateTime.of(2020, 1, 1, 12, 0), 60, 7);
        assertNull(e);
    }
    @Test
    public void RDVPeriodiqueFrequenceNegative(){
        Event e = FabriqueEvent.getEventPeriodique("RDV", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, -7);
        assertNull(e);
    }
    @Test
    public void RDVPeriodiqueFrequenceNulle(){
        Event e = FabriqueEvent.getEventPeriodique("RDV", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, 0);
        assertNull(e);
    }
    //----------------Reunion----------------
    @Test
    public void ReunionClassic(){
        EventReunion e = (EventReunion) FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60, "ici", "moi");
        assertEquals("REUNION", e.type);
        assertEquals("Reunion", e.title);
        assertEquals("moi", e.proprietaire);
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.dateDebut);
        assertEquals(60, e.dureeMinutes);
        assertEquals("ici", e.lieu);
        assertEquals("moi", e.participants);
    }
    @Test
    public void ReunionNullProprio(){
        Event e = FabriqueEvent.getEventReunion("Reunion", null, LocalDateTime.of(2021, 1, 1, 12, 0), 60, "ici", "moi");
        assertNull(e);
    }
    @Test
    public void ReunionNullDate(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", null, 60, "ici", "moi");
        assertNull(e);
    }
    @Test
    public void ReunionDatePassée(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2020, 1, 1, 12, 0), 60, "ici", "moi");
        assertNull(e);
    }
    @Test
    public void ReunionNullLieu(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, null, "moi");
        assertNull(e);
    }
    @Test
    public void ReunionLieuVide(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, "", "moi");
        assertNull(e);
    }
    @Test
    public void ReunionNullParticipants(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, "ici", null);
        assertNull(e);
    }
    @Test
    public void ReunionParticipantsVide(){
        Event e = FabriqueEvent.getEventReunion("Reunion", "moi", LocalDateTime.of(2021, 1, 1, 12, 0), 60, "ici", "");
        assertNull(e);
    }



}
