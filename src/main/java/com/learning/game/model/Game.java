package com.learning.game.model;

import com.learning.game.service.Referee;
import net.jcip.annotations.Immutable;

@Immutable
public final class Game {
    public enum PLAYER_HAND {
        ROCK, PAPER, SCISSORS;
    }

    private final PLAYER_HAND player1;
    private final PLAYER_HAND player2;
    private final Referee.RESULTS winner;

    public Game(final PLAYER_HAND player1, final PLAYER_HAND player2, final Referee.RESULTS winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public PLAYER_HAND getPlayer1() {
        return this.player1;
    }

    public PLAYER_HAND getPlayer2() {
        return this.player2;
    }

    public Referee.RESULTS getWinner() {
        return this.winner;
    }
}
