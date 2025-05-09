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
    void shouldReturnGamesSortedByTotalScoreAndCreationTime() {
        scoreboard.startGame("NewYork", "Seatle");
        scoreboard.startGame("Gdynia", "Malbork");
        scoreboard.startGame("Warszawa", "Łódź");
        scoreboard.startGame("Kutno", "Łowicz");

        scoreboard.updateScore("Warszawa", "Łódź", 2, 5);
        scoreboard.updateScore("Gdynia", "Malbork", 2, 5);

        List<Game> summary = scoreboard.getSummary();

        assertEquals("Gdynia", summary.get(0).getHomeTeam());
        assertEquals("Warszawa", summary.get(1).getHomeTeam());
        assertEquals("NewYork", summary.get(2).getHomeTeam());
        assertEquals("Kutno", summary.get(3).getHomeTeam());
    }

}
