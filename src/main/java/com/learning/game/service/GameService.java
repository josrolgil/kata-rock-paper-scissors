package com.learning.game.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Stats;
import com.learning.game.service.interfaces.GameData;
import com.learning.game.service.interfaces.RoundProcessor;
import com.learning.game.service.interfaces.StatisticProcessor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@ThreadSafe
@Service
public final class GameService {
  private static final Logger LOG = LogManager.getLogger(GameService.class);

  private final GameData gameData;
  private final StatisticProcessor statisticProcessor;
  private final RoundProcessor roundProcessor;

  @Autowired
  public GameService(
      final GameData gameData,
      final StatisticProcessor statisticProcessor,
      final RoundProcessor roundProcessor) {
    this.gameData = gameData;
    this.statisticProcessor = statisticProcessor;
    this.roundProcessor = roundProcessor;
  }

  public GameRound playRound() {
    LOG.debug("GameService: playing new round");
    final GameRound gameRound = roundProcessor.playRound();
    this.gameData.saveRoundData(gameRound);
    return gameRound;
  }

  public Stats processStatistics() {
    LOG.debug("GameService: processingStats");
    final List<GameRound> rounds = this.gameData.retrieveAllRounds();
    return statisticProcessor.getStats(rounds);
  }
}
