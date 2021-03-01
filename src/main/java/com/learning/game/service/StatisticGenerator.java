package com.learning.game.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Result;
import com.learning.game.model.Stats;
import com.learning.game.service.interfaces.StatisticProcessor;
import net.jcip.annotations.Immutable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Immutable
public class StatisticGenerator implements StatisticProcessor {
    @Override
    public Stats getStats(final List<GameRound> rounds) {
        long totalGames = 0l;
        long totalPlayer1Wins = 0l;
        long totalPlayer2Wins = 0l;
        long totalDraws = 0l;

        if (rounds != null) {
            for (final GameRound round : rounds) {
                totalGames++;
                if (round.getWinner().equals(Result.P1)) {
                    totalPlayer1Wins++;
                } else if (round.getWinner().equals(Result.P2)) {
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
