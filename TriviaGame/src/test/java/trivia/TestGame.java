package trivia;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private Game game;
    @BeforeEach
    void setUp() {
        System.out.println("TestGame.setUp");
         game = new Game();
         game.add("Joueur1");
         game.add("Joueur2");

    }

    @Test
    @Disabled("Ajout de Joueur")
    public void addPlayer() {
        Game game = new Game();
        game.add("Joueur1");
        assertEquals(1, game.howManyPlayers());
    }
    @Test
    @Disabled("Ajout plusieurs Joueur")
    void addMultiplePlayers() {
        Game game = new Game();
        game.add("Joueur1");
        game.add("Joueur2");
        assertEquals(2, game.howManyPlayers());
    }
    @Test
    @Disabled("Creation de question")
    void createQuestion() {
        assertEquals("Science Question 1", game.createQuestion("Science", 1));
    }

    @Test
    @Disabled("Roll classsic")
    void roll() {
        game.roll(5);
        assertEquals(6, game.currentPlayer.getPlace());
    }
    @Test
    @Disabled("Roll prison")
    void rollPrison() {
        game.roll(2);
        assertEquals(1, game.currentPlayer.getPlace());
    }

}
