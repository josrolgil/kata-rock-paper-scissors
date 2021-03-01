package com.learning.game.service;

import com.learning.game.model.Player;
import com.learning.game.model.Result;
import net.jcip.annotations.Immutable;

@Immutable
public final class GameReferee {
    private final Player player1;
    private final Player player2;

    public GameReferee(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Result judge() {
        if (player1 == player2) {
            return Result.DRAW;
        } else if (player1 == Player.ROCK && player2 == Player.SCISSORS) {
            return Result.P1;
        } else if (player1 == Player.ROCK && player2 == Player.PAPER) {
            return Result.P2;
        } else if (player1 == Player.SCISSORS && player2 == Player.ROCK) {
            return Result.P2;
        } else if (player1 == Player.SCISSORS && player2 == Player.PAPER) {
            return Result.P1;
        } else if (player1 == Player.PAPER && player2 == Player.SCISSORS) {
            return Result.P2;
        } else if (player1 == Player.PAPER && player2 == Player.ROCK) {
            return Result.P1;
        } else {
            throw new IllegalStateException("Can not determine winner");
        }
    }
}
