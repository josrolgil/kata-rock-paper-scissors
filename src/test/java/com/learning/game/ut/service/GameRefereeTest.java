package com.learning.game.ut.service;

import com.learning.game.model.Player;
import com.learning.game.model.Result;
import com.learning.game.service.GameReferee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class GameRefereeTest {
    private static Stream<Arguments> getGameRounds() {
        return List.of(
                Arguments.of(Player.PAPER, Player.PAPER, Result.DRAW),
                Arguments.of(Player.ROCK, Player.ROCK, Result.DRAW),
                Arguments.of(Player.SCISSORS, Player.SCISSORS, Result.DRAW),
                Arguments.of(Player.PAPER, Player.ROCK, Result.P1),
                Arguments.of(Player.SCISSORS, Player.PAPER, Result.P1),
                Arguments.of(Player.ROCK, Player.SCISSORS, Result.P1),
                Arguments.of(Player.PAPER, Player.SCISSORS, Result.P2),
                Arguments.of(Player.ROCK, Player.PAPER, Result.P2),
                Arguments.of(Player.SCISSORS, Player.ROCK, Result.P2)
        ).stream();
    }

    @DisplayName(value = "Testing the referee decisions")
    @ParameterizedTest(name = "{index} - {1}")
    @MethodSource(value = {"getGameRounds"})
    public void testGetStats(final Player player1, final Player player2, final Result expectedResult) {
        //Given player1, player2
        final GameReferee referee = new GameReferee(player1, player2);
        //When
        final Result result = referee.judge();
        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}
