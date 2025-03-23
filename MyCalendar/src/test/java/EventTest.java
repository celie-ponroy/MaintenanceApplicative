import org.example.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventTest {
    //------------Rdv perso----------------
    @Test
    public void RdvpersoClassic(){
        Event e = FabriqueEvent.getEventRDV("RDV", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60);
        assertEquals(EventType.RDV_PERSONNEL, e.getType());
        assertEquals("RDV", e.getTitle());
        assertEquals("moi", e.getProprietaire());
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.getDateDebut());
        assertEquals(60, e.getDureeMinutes());
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
        assertEquals(EventType.PERIODIQUE, e.getType());
        assertEquals("RDV", e.getTitle());
        assertEquals("moi", e.getProprietaire());
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.getDateDebut());
        assertEquals(60, e.getDureeMinutes());
        assertEquals(7, e.getFrequenceJours());
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
        assertEquals(EventType.REUNION, e.getType());
        assertEquals("Reunion", e.getTitle());
        assertEquals("moi", e.getProprietaire());
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.getDateDebut());
        assertEquals(60, e.getDureeMinutes());
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

    //------------Rdv souscription----------------
   @Test
    public void SouscriptionClassic(){
        Event e = FabriqueEvent.getEventSouscription("Souscription", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60, 12.0,"Free");
        assertEquals(EventType.SOUSCRIPTION, e.getType());
        assertEquals("Souscription", e.getTitle());
        assertEquals("moi", e.getProprietaire());
        assertEquals(LocalDateTime.of(2026, 1, 1, 12, 0), e.getDateDebut());
        assertEquals(60, e.getDureeMinutes());
        assertEquals(12.0, ((EventSouscription) e).getPrix());
        assertEquals("Free", ((EventSouscription) e).getEntreprise());
   }
   @Test
    public void SouscriptionNullProprio(){
       Event e = FabriqueEvent.getEventSouscription("Souscription", null, LocalDateTime.of(2026, 1, 1, 12, 0), 60, 12.0,"Free");
       assertNull(e);
    }
    @Test
    public void SouscriptionNullDate(){
        Event e = FabriqueEvent.getEventSouscription("Souscription", "moi", null, 60, 12.0,"Free");
        assertNull(e);
    }
    @Test
    public void SouscriptionNullEntreprise(){
        EventSouscription e = FabriqueEvent.getEventSouscription("Souscription", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60, 12.0,null);
        assertEquals("Inconnue",e.getEntreprise());

    }
    @Test
    public void SouscriptionVideEntreprise(){
        EventSouscription e = FabriqueEvent.getEventSouscription("Souscription", "moi", LocalDateTime.of(2026, 1, 1, 12, 0), 60, 12.0,"");
        assertEquals("Inconnue",e.getEntreprise());

    }


}
