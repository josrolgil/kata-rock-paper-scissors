package com.learning.game.service;

import com.learning.game.model.Game;
import com.learning.game.model.Stats;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public final class GameService {
    private final List<Game> games;

    public GameService() {
        games = new ArrayList<>();
    }

    public void addGame(final Game game) {
        synchronized (games) {
            this.games.add(game);
        }
    }

    public Stats getStats() {
        long totalGames = 0l;
        long totalPlayer1Wins = 0l;
        long totalPlayer2Wins = 0l;
        long totalDraws = 0l;

        synchronized (games) {
            for (final Game game : games) {
                totalGames++;
                if (game.getWinner().equals(Referee.RESULTS.P1)) {
                    totalPlayer1Wins++;
                } else if (game.getWinner().equals(Referee.RESULTS.P2)) {
                    totalPlayer2Wins++;
                } else {
                    totalDraws++;
                }
            }
        }

        return new Stats(
                totalGames, totalPlayer1Wins, totalPlayer2Wins, totalDraws
        );
    }
}
