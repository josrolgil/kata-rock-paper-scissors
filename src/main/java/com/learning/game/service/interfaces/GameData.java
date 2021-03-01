package com.learning.game.service.interfaces;

import com.learning.game.model.GameRound;

import java.util.List;

public interface GameData {
    void saveRoundData(final GameRound round);

    List<GameRound> retrieveAllRounds();
}
