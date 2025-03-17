package trivia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void newPlayerNormal() {
        Player player = new Player("Joueur1");
        assertEquals("Joueur1", player.getName());
        assertEquals(1, player.getPlace());
        assertEquals(0, player.getPurse());
    }
    @Test
    public void newPlayerEmptyName() {
        Player player = new Player("");
        assertEquals("", player.getName());
        assertEquals(1, player.getPlace());
        assertEquals(0, player.getPurse());
    }

    @Test
    public void movePlayer() {
        Player player = new Player("Joueur1");
        player.move(5);
        assertEquals(6, player.getPlace());
    }
    @Test
    public void movePlayerMore12() {
        Player player = new Player("Joueur1");
        player.move(12);
        assertEquals(1, player.getPlace());
    }
    @Test
    public void gotoPenalty() {
        Player player = new Player("Joueur1");
        player.goToPenaltyBox();
        assertEquals(true, player.isInPenaltyBox());
    }
    @Test
    public void getOutPenalty() {
        Player player = new Player("Joueur1");
        player.goToPenaltyBox();
        player.getOutPenalityBox();
        assertEquals(false, player.isInPenaltyBox());
    }
    @Test
    public void addCoin() {
        Player player = new Player("Joueur1");
        player.addCoin(5);
        assertEquals(5, player.getPurse());
    }

}
