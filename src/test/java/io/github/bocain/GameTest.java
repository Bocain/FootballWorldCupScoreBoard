package io.github.bocain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldCreateGameWithZeroScore() {
        Game game = new Game("Poland", "Germany");
        assertEquals(0, game.getTotalScore());
        assertNotNull(game.getCreatedAt());
    }

    @Test
    void shouldUpdateScoreCorrectly() {
        Game game = new Game("Brazil", "Argentina");
        game.updateScore(2, 3);
        assertEquals(5, game.getTotalScore());
    }

    @Test
    void gamesWithSameTeamsShouldBeEqual() {
        Game g1 = new Game("Spain", "France");
        Game g2 = new Game("Spain", "France");
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void gamesWithDifferentTeamsShouldNotBeEqual() {
        Game g1 = new Game("Spain", "France");
        Game g2 = new Game("Spain", "Germany");
        assertNotEquals(g1, g2);
    }

    @Test
    void toStringShouldBeFormatted() {
        Game game = new Game("Italy", "England");
        game.updateScore(1, 1);
        String text = game.toString();
        assertTrue(text.contains("Italy"));
        assertTrue(text.contains("England"));
        assertTrue(text.contains("1 - 1"));
    }

    @Test
    void createdAtShouldNotBeNull() {
        Game game = new Game("Poland", "Germany");
        assertNotNull(game.getCreatedAt());
    }

}
