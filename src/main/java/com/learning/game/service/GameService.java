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

@ThreadSafe
@Service
public final class GameService {
    private final GameData gameData;
    private final StatisticProcessor statisticProcessor;
    private final RoundProcessor roundProcessor;

    @Autowired
    public GameService(final GameData gameData, final StatisticProcessor statisticProcessor, final RoundProcessor roundProcessor) {
        this.gameData = gameData;
        this.statisticProcessor = statisticProcessor;
        this.roundProcessor = roundProcessor;
    }

    public GameRound playRound() {
        final GameRound gameRound = roundProcessor.processRound();
        this.gameData.saveRoundData(gameRound);
        return gameRound;
    }

    public Stats getStats() {
        final List<GameRound> rounds = this.gameData.retrieveAllRounds();
        return statisticProcessor.getStats(rounds);
    }
}
