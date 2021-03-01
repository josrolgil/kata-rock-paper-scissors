package com.learning.game.ut.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import com.learning.game.model.Stats;
import com.learning.game.service.interfaces.StatisticProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class StatisticGeneratorTest {
  @Autowired private StatisticProcessor statisticProcessor;

  private static Stream<Arguments> getRounds() {
    return List.of(
        Arguments.of(
            List.of(
                new GameRound(Player.SCISSORS, Player.PAPER, Result.P1),
                new GameRound(Player.SCISSORS, Player.PAPER, Result.P1),
                new GameRound(Player.ROCK, Player.PAPER, Result.P2),
                new GameRound(Player.PAPER, Player.PAPER, Result.DRAW)),
            new Stats(4L, 2L, 1L, 1L)),
        Arguments.of(List.of(), new Stats(0L, 0L, 0L, 0L)),
        Arguments.of(null, new Stats(0L, 0L, 0L, 0L)))
        .stream();
  }

  @DisplayName(value = "Testing the generation of stats given a list of rounds")
  @ParameterizedTest(name = "{index} - {1}")
  @MethodSource(value = {"getRounds"})
  public void testGetStats(final List<GameRound> rounds, final Stats expectedStats) {
    // Given rounds
    // When
    final Stats stats = statisticProcessor.getStats(rounds);
    // Assert
    Assertions.assertEquals(expectedStats.getTotalGames(), stats.getTotalGames());
    Assertions.assertEquals(expectedStats.getTotalPlayer1Wins(), stats.getTotalPlayer1Wins());
    Assertions.assertEquals(expectedStats.getTotalPlayer2Wins(), stats.getTotalPlayer2Wins());
    Assertions.assertEquals(expectedStats.getTotalDraws(), stats.getTotalDraws());
  }
}
