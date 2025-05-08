package io.github.bocain;

import java.util.*;
import java.util.stream.Collectors;

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
                        .thenComparing(Game::getCreatedAt))
                .collect(Collectors.toList());
    }

    private Game findGame(String homeTeam, String awayTeam) {
        return games.stream()
                .filter(g -> g.getHomeTeam().equals(homeTeam) && g.getAwayTeam().equals(awayTeam))
                .findFirst()
                .get();
    }

}

