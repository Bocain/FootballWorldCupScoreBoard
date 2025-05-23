package io.github.bocain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new ScoreBoard();
    }

    @Test
    void shouldStartGameSuccessfully() {
        scoreboard.startGame("Poland", "USA");
        Game game = scoreboard.getSummary().stream().findFirst().get();
        assertEquals("Poland 0 - 0 USA", game.toString());
    }

    @Test
    void shouldUpdateScoreSuccessfully() {
        scoreboard.startGame("Belgium", "Netherlands");
        scoreboard.updateScore("Belgium", "Netherlands", 2, 3);
        Game game = scoreboard.getSummary().stream().findFirst().get();
        assertEquals("Belgium 2 - 3 Netherlands", game.toString());
    }

    @Test
    void shouldFinishGameSuccessfully() {
        scoreboard.startGame("Italy", "France");
        scoreboard.finishGame("Italy", "France");
        List<Game> summary = scoreboard.getSummary();
        assertTrue(summary.stream().toList().isEmpty());
    }

    @Test
    void shouldReturnGamesSortedByTotalScoreAndCreationOrder() {
        scoreboard.startGame("NewYork", "Seatle");
        scoreboard.startGame("Gdynia", "Malbork");
        scoreboard.startGame("Warszawa", "Łódź");
        scoreboard.startGame("Kutno", "Łowicz");

        scoreboard.updateScore("Warszawa", "Łódź", 2, 5);
        scoreboard.updateScore("Gdynia", "Malbork", 2, 5);

        List<Game> summary = scoreboard.getSummary();

        // Games with same total score should be sorted by sequenceId (latest first)
        assertEquals("Warszawa", summary.get(0).getHomeTeam()); // same score, but started later
        assertEquals("Gdynia", summary.get(1).getHomeTeam());
        assertEquals("Kutno", summary.get(2).getHomeTeam());
        assertEquals("NewYork", summary.get(3).getHomeTeam());
    }

    @Test
    void testStartDuplicateGameThrowsException() {
        scoreboard.startGame("Team A", "Team B");
        assertEquals("The match is already underway: Team A vs Team B",
                assertThrows(IllegalStateException.class,
                        () -> scoreboard.startGame("Team A", "Team B")).getMessage());
    }

    @Test
    void testUpdateScoreOnNonExistingGameThrowsException() {
        assertEquals("No match found: Team A vs Team B",
                assertThrows(NoSuchElementException.class,
                        () -> scoreboard.updateScore("Team A", "Team B", 1, 1)).getMessage());
    }

    @Test
    void testFinishNonExistingGameThrowsException() {
        assertEquals("No match found: Team A vs Team B",
                assertThrows(NoSuchElementException.class,
                        () -> scoreboard.finishGame("Team A", "Team B")).getMessage());
    }

}
