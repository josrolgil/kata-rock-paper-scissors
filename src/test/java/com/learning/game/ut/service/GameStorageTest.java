package com.learning.game.ut.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import com.learning.game.service.GameStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameStorageTest {

    @Test
    @DisplayName(value = "Testing game storage implementation")
    public void testStoreData() {
        //Given
        final GameRound gameRound = new GameRound(Player.SCISSORS, Player.ROCK, Result.P2);
        final GameStorage gameStorage = new GameStorage();
        //When
        gameStorage.saveRoundData(gameRound);
        final List<GameRound> rounds = gameStorage.retrieveAllRounds();
        //Assert
        Assertions.assertEquals(1, rounds.size());
        Assertions.assertEquals(gameRound, rounds.get(0));
    }
}
