import org.example.GestionnaireConnexion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionnaireConnexionTest {
    GestionnaireConnexion main;
    @BeforeEach
    public void setUp() {
        main = new GestionnaireConnexion();
    }
    @Test
    public void testConnectionConnue() {
        main.connexion("Roger","Chat");
        assertEquals("Roger",main.getUtilisateur().getNom());
    }
    @Test
    public void testConnectionMauvaisMotDePasse() {
        main.connexion("Roger","Chien");
        assertNull(main.getUtilisateur());
    }
    @Test
    public void testConnectionInconnue(){
        main.connexion("Célie","12");
        assertNull(main.getUtilisateur());
    }
    @Test
    public void testInscription(){
        main.inscription("Célie","12","12");
        assertEquals(main.getUtilisateur().getNom(),"Célie");
    }
    @Test
    public void testInscriptionDejaPris(){
        main.inscription("Roger","Chat","Chat");
        assertNull(main.getUtilisateur());
    }
    @Test
    public void testInscriptionMdpDiff(){
        main.inscription("Célie","12","13");
        assertNull(main.getUtilisateur());
    }
    @Test
    public void testConnexionApresInscription(){
        main.inscription("Célie","12","12");
        main.deconnexion();
        main.connexion("Célie","12");
        assertEquals(main.getUtilisateur().getNom(),"Célie");
    }
    @Test
    public void testConnexionApresInscriptionMdpDiff(){
        main.inscription("Célie","12","12");
        main.deconnexion();
        main.connexion("Célie","13");
        assertNull(main.getUtilisateur());
    }
}
