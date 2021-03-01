package com.learning.game.service;

import com.learning.game.model.GameRound;
import com.learning.game.service.interfaces.GameData;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ThreadSafe
@Service
public final class GameStorage implements GameData {
  private final List<GameRound> rounds;

  public GameStorage() {
    rounds = new ArrayList();
  }

  @Override
  public void saveRoundData(final GameRound game) {
    synchronized (rounds) {
      this.rounds.add(game);
    }
  }

  @Override
  public List<GameRound> retrieveAllRounds() {
    synchronized (rounds) {
      return Collections.unmodifiableList(rounds);
    }
  }
}
