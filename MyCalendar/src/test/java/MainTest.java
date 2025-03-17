import org.example.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    Main main;
    @BeforeEach
    public void setUp() {
        main = new Main();
    }
    @Test
    public void testConnectionConnue() {
        main.connexion("Roger","Chat");
        assertTrue(main.getUtilisateur().equals("Roger"));
    }
    @Test
    public void testConnectionMauvaisMotDePasse() {
        main.connexion("Roger","Chien");
        assertTrue(main.getUtilisateur()==null);
    }
    @Test
    public void testConnectionInconnue(){
        main.connexion("Célie","12");
        assertTrue(main.getUtilisateur()==null);
    }
    @Test
    public void testInscription(){
        main.inscription("Célie","12","12");
        assertTrue(main.getUtilisateur().equals("Célie"));
    }
    @Test
    public void testInscriptionDejaPris(){
        main.inscription("Roger","Chat","Chat");
        assertTrue(main.getUtilisateur()==null);
    }
    @Test
    public void testInscriptionMdpDiff(){
        main.inscription("Célie","12","13");
        assertTrue(main.getUtilisateur()==null);
    }
    @Test
    public void testConnexionApresInscription(){
        main.inscription("Célie","12","12");
        main.deconnexion();
        main.connexion("Célie","12");
        assertTrue(main.getUtilisateur().equals("Célie"));
    }
    @Test
    public void testConnexionApresInscriptionMdpDiff(){
        main.inscription("Célie","12","12");
        main.deconnexion();
        main.connexion("Célie","13");
        assertTrue(main.getUtilisateur()==null);
    }
}
