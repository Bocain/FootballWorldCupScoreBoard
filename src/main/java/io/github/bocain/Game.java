package io.github.bocain;

import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"homeTeam", "awayTeam"})
public class Game {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final LocalDateTime createdAt;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.createdAt = LocalDateTime.now();
    }

    public void updateScore(int home, int away) {
        this.homeScore = home;
        this.awayScore = away;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getTotalScore() { return homeScore + awayScore; }

    @Override
    public String toString() {
        return String.format("%s %d - %d %s", homeTeam, homeScore, awayScore, awayTeam);
    }

}
