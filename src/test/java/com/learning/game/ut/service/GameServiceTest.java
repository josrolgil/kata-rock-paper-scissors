package com.learning.game.ut.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Stats;
import com.learning.game.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameServiceTest {
  @Autowired private GameService gameService;

  @Test
  @DisplayName("Testing playing some rounds and gathering statatistics")
  public void testGameService() {
    // When first play
    final GameRound gameRound1 = gameService.playRound();
    final GameRound gameRound2 = gameService.playRound();
    // When second gather statistics
    final Stats stats = gameService.getStats();

    // Assert
    Assertions.assertNotNull(gameRound1);
    Assertions.assertNotNull(gameRound2);

    Assertions.assertNotNull(stats);
    Assertions.assertEquals(2, stats.getTotalGames());
  }
}
