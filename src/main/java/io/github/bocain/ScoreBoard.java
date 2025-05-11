package io.github.bocain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a collection of ongoing football games and their current scores.
 * <p>
 * Provides methods to start new games, update their scores,
 * finish games, and retrieve a summary of active games sorted by total score and creation time.
 * <p>
 *
 * @author Kamil Kaliński
 */
class ScoreBoard {

    private final Set<Game> games = new HashSet<>();

    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        Game game = findGame(homeTeam, awayTeam);
        game.updateScore(homeTeamScore, awayTeamScore);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.remove(findGame(homeTeam, awayTeam));
    }

    public List<Game> getSummary() {
        return games.stream()
                .sorted(Comparator.comparingInt(Game::getTotalScore)
                        .reversed()
                        .thenComparing(Game::getSequenceId))
                .collect(Collectors.toList());
    }

    private Game findGame(String homeTeam, String awayTeam) {
        return games.stream()
                .filter(g -> g.getHomeTeam().equals(homeTeam) && g.getAwayTeam().equals(awayTeam))
                .findFirst()
                .get();
    }

}

