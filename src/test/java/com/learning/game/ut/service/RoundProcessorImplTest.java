package com.learning.game.ut.service;

import com.learning.game.model.GameRound;
import com.learning.game.service.interfaces.RoundProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoundProcessorImplTest {
  @Autowired private RoundProcessor roundProcessor;

  @DisplayName(value = "Testing the generation of game rounds")
  @Test
  public void testGetGameRound() {
    // Given rounds
    // When
    final GameRound gameRound = roundProcessor.playRound();
    // Assert
    Assertions.assertNotNull(gameRound);
    Assertions.assertNotNull(gameRound.getWinner());
    Assertions.assertNotNull(gameRound.getPlayer1());
    Assertions.assertNotNull(gameRound.getPlayer2());
  }
}
