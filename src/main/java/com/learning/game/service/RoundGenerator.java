package com.learning.game.service;

import com.learning.game.controller.StatsController;
import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import com.learning.game.service.interfaces.RoundProcessor;
import net.jcip.annotations.Immutable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Immutable
public class RoundGenerator implements RoundProcessor {
  private static final Logger LOG = LogManager.getLogger(RoundGenerator.class);


  @Override
  public GameRound processRound() {
    final Player player1 = RandomHand.obtainRandomHand();
    final Player player2 = Player.ROCK;
    final GameReferee gameReferee = new GameReferee(player1, player2);
    final Result result = gameReferee.judge();
    LOG.debug("Game played. P1 {} P2 {} Result {} ", player1, player2, result);
    return new GameRound(player1, player2, result);
  }
}
