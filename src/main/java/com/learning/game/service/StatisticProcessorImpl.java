package com.learning.game.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Result;
import com.learning.game.model.Stats;
import com.learning.game.service.interfaces.StatisticProcessor;
import net.jcip.annotations.Immutable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Immutable
public final class StatisticProcessorImpl implements StatisticProcessor {
  private static final Logger LOG = LogManager.getLogger(StatisticProcessorImpl.class);

  @Override
  public Stats getStats(final List<GameRound> rounds) {
    long totalGames = 0l;
    long totalPlayer1Wins = 0l;
    long totalPlayer2Wins = 0l;
    long totalDraws = 0l;

    if (rounds != null) {
      totalGames = rounds.size();
      totalPlayer1Wins =
          rounds.stream().filter(gameRound -> gameRound.getWinner().equals(Result.P1)).count();
      totalPlayer2Wins =
          rounds.stream().filter(gameRound -> gameRound.getWinner().equals(Result.P2)).count();
      totalDraws =
          rounds.stream().filter(gameRound -> gameRound.getWinner().equals(Result.DRAW)).count();
    }
    LOG.debug("Generating stats: totalGames={}, totalPlayer1Wins={},  totalPlayer2Wins={}, totalDraws={}",
            totalGames, totalPlayer1Wins, totalPlayer2Wins, totalDraws);
    return new Stats(totalGames, totalPlayer1Wins, totalPlayer2Wins, totalDraws);
  }
}
