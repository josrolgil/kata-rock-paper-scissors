package com.learning.game.model;

import net.jcip.annotations.Immutable;

@Immutable
public final class GameRound {
  private final Player player1;
  private final Player player2;
  private final Result winner;

  public GameRound(final Player player1, final Player player2, final Result winner) {
    this.player1 = player1;
    this.player2 = player2;
    this.winner = winner;
  }

  public Player getPlayer1() {
    return this.player1;
  }

  public Player getPlayer2() {
    return this.player2;
  }

  public Result getWinner() {
    return this.winner;
  }
}
