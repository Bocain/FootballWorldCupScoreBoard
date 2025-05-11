package io.github.bocain;

import java.util.*;
import java.util.stream.Collectors;

class ScoreBoard {

    private final Set<Game> games = new HashSet<>();

    public final void startGame(String homeTeam, String awayTeam) {
        Game newGame = new Game(homeTeam, awayTeam);
        if (games.contains(newGame)) {
            throw new IllegalStateException(String.format("The match is already underway: %s vs %s", homeTeam, awayTeam));
        }
        games.add(newGame);
    }

    public final void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        Game game = findGame(homeTeam, awayTeam);
        game.updateScore(homeTeamScore, awayTeamScore);
    }

    public final void finishGame(String homeTeam, String awayTeam) {
        games.remove(findGame(homeTeam, awayTeam));
    }

    public final List<Game> getSummary() {
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
                .orElseThrow(() -> new NoSuchElementException(String.format("No match found: %s vs %s", homeTeam, awayTeam)));
    }

}

