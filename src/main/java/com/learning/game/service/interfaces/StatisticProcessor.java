package com.learning.game.service.interfaces;

import com.learning.game.model.GameRound;
import com.learning.game.model.Stats;

import java.util.List;

public interface StatisticProcessor {
  Stats getStats(final List<GameRound> rounds);
}
