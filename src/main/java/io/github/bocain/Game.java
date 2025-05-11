package io.github.bocain;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public final class Game {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private final long sequenceId;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.sequenceId =  ID_GENERATOR.incrementAndGet();
    }

    public void updateScore(int home, int away) {
        this.homeScore = home;
        this.awayScore = away;
    }

    public long getSequenceId() {
        return sequenceId;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %d %s", homeTeam, homeScore, awayScore, awayTeam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(homeTeam, game.homeTeam) && Objects.equals(awayTeam, game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }


}
