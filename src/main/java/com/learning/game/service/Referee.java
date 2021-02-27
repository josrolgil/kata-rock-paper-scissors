package com.learning.game.service;

import com.learning.game.model.Game;
import net.jcip.annotations.Immutable;

@Immutable
public final class Referee {
    public enum RESULTS {
        P1, P2, DRAW;
    }

    private Game.PLAYER_HAND player1;
    private Game.PLAYER_HAND player2;

    public Referee(final Game.PLAYER_HAND player1, final Game.PLAYER_HAND player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public RESULTS selectWinner() {
        if (player1 == player2) {
            return RESULTS.DRAW;
        } else if (player1 == Game.PLAYER_HAND.ROCK && player2 == Game.PLAYER_HAND.SCISSORS) {
            return RESULTS.P1;
        } else if (player1 == Game.PLAYER_HAND.ROCK && player2 == Game.PLAYER_HAND.PAPER) {
            return RESULTS.P2;
        } else if (player1 == Game.PLAYER_HAND.SCISSORS && player2 == Game.PLAYER_HAND.ROCK) {
            return RESULTS.P2;
        } else if (player1 == Game.PLAYER_HAND.SCISSORS && player2 == Game.PLAYER_HAND.PAPER) {
            return RESULTS.P1;
        } else if (player1 == Game.PLAYER_HAND.PAPER && player2 == Game.PLAYER_HAND.SCISSORS) {
            return RESULTS.P2;
        } else if (player1 == Game.PLAYER_HAND.PAPER && player2 == Game.PLAYER_HAND.ROCK) {
            return RESULTS.P1;
        } else {
            throw new IllegalStateException("Can not determine winner");
        }
    }
}
