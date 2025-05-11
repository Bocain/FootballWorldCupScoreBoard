package io.github.bocain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldCreateGameWithZeroScore() {
        Game game = new Game("Poland", "Germany");
        assertEquals(0, game.getTotalScore());
        assertNotNull(game.getSequenceId());
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
        assertTrue(game.toString().equals("Italy 1 - 1 England"));
    }

    @Test
    void createdAtShouldNotBeNull() {
        Game game = new Game("Poland", "Germany");
        assertNotNull(game.getSequenceId());
    }

    @Test
    void testConstructorSameTeamsThrowsException() {
        assertEquals("Teams must be different.",
                assertThrows(IllegalArgumentException.class,
                        () -> new Game("Team A", "Team A")).getMessage());
    }

    @Test
    void testConstructorNullOrBlankThrowsException() {
        assertEquals("Team names cannot be empty.",
                assertThrows(IllegalArgumentException.class,
                        () -> new Game(null, "Team B")).getMessage());

        assertEquals("Team names cannot be empty.",
                assertThrows(IllegalArgumentException.class,
                        () -> new Game("Team A", null)).getMessage());

        assertEquals("Team names cannot be empty.",
                assertThrows(IllegalArgumentException.class,
                        () -> new Game(" ", "Team B")).getMessage());

        assertEquals("Team names cannot be empty.",
                assertThrows(IllegalArgumentException.class,
                        () -> new Game("Team A", "")).getMessage());
    }

    @Test
    void testUpdateScoreNegativeThrowsException() {
        Game game = new Game("Team A", "Team B");

        assertEquals("Results cannot be negative.",
                assertThrows(IllegalArgumentException.class,
                        () -> game.updateScore(-1, 2)).getMessage());

        assertEquals("Results cannot be negative.",
                assertThrows(IllegalArgumentException.class,
                        () -> game.updateScore(2, -1)).getMessage());
    }

}
