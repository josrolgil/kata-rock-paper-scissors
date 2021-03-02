package com.learning.game.ut.controller;

import com.learning.game.controller.RoundValidator;
import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class RoundValidatorTest {
    @Autowired private RoundValidator sessionValidator;

    private static Stream<Arguments> getRounds() {
        return List.of(
                Arguments.of(null, new ArrayList<>()),
                Arguments.of(
                        List.of(new GameRound(Player.ROCK, Player.ROCK, Result.DRAW),
                        new GameRound(Player.ROCK, Player.ROCK, Result.DRAW),
                        new GameRound(Player.ROCK, Player.ROCK, Result.DRAW)),
                                new ArrayList<>()),
                Arguments.of(
                        List.of(new GameRound(Player.ROCK, Player.ROCK, Result.DRAW)),
                        List.of(new GameRound(Player.ROCK, Player.ROCK, Result.DRAW))
                )
        )
                .stream();
    }

    @DisplayName(value = "Testing the round list validation")
    @ParameterizedTest(name = "{index} - {1}")
    @MethodSource(value = {"getRounds"})
    public void testGetStats(
            final List<GameRound> input, final List<GameRound> expectedResult) {
        // Given input and expected Result
        // When
        final List<GameRound> result = sessionValidator.getValidatedRounds(input);
        // Assert
        Assertions.assertEquals(expectedResult.size(), result.size());
    }

}
