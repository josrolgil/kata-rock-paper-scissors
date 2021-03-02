package com.learning.game.controller;

import com.learning.game.model.GameRound;
import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ThreadSafe
public final class RoundValidator {
  @Value("${security.games.max}")
  private int max_game_rounds;

  /** Avoid DoS attack by increasing the value of the list of rounds to infinite. Set a threshold */
  public List<GameRound> getValidatedRounds(final List<GameRound> gameRounds) {
    if (gameRounds == null) {
      return new ArrayList<>();
    }
    return gameRounds.size() > max_game_rounds ? new ArrayList<>() : gameRounds;
  }
}
