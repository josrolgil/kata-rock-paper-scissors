package com.learning.game.model;

import net.jcip.annotations.Immutable;

@Immutable
public final class Stats {

  private final long totalGames;
  private final long totalPlayer1Wins;
  private final long totalPlayer2Wins;
  private final long totalDraws;

  public Stats(
      final long totalGames,
      final long totalPlayer1Wins,
      final long totalPlayer2Wins,
      final long totalDraws) {
    this.totalGames = totalGames;
    this.totalPlayer1Wins = totalPlayer1Wins;
    this.totalPlayer2Wins = totalPlayer2Wins;
    this.totalDraws = totalDraws;
  }

  public long getTotalGames() {
    return this.totalGames;
  }

  public long getTotalPlayer1Wins() {
    return this.totalPlayer1Wins;
  }

  public long getTotalPlayer2Wins() {
    return this.totalPlayer2Wins;
  }

  public long getTotalDraws() {
    return this.totalDraws;
  }
}
