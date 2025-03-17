import org.example.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilisateurTest {
    private Utilisateur u;
    @BeforeEach
    public void setUp() {
        u = new Utilisateur("Alice", "1234");
    }
    @Test
    public void testEquals() {
        Utilisateur u2 = new Utilisateur("Alice", "1234");
        assertTrue(u.equals(u2));
    }
    @Test
    public void testNotEquals() {
        Utilisateur u2 = new Utilisateur("Bob", "5678");
        assertFalse(u.equals(u2));
    }
    @Test
    public void motDePasseIncorrect() {
        assertFalse(u.motDePasseCorrect("5678"));
    }
    @Test
    public void motDePasseCorrect() {
        assertTrue(u.motDePasseCorrect("1234"));
    }
}
