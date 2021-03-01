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

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (this.getClass() != o.getClass()) return false;
    final GameRound gameRound = (GameRound) o;
    return player1 == gameRound.player1
            && player2 == gameRound.player2
            && winner == gameRound.winner;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + player1.hashCode();
    hash = 31 * hash + player2.hashCode();
    hash = 31 * hash + winner.hashCode();
    return hash;
  }
}
