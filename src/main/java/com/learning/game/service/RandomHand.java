package com.learning.game.service;

import com.learning.game.model.Game;
import net.jcip.annotations.ThreadSafe;

import java.util.Random;

@ThreadSafe
public final class RandomHand {
    private static final Random random = new Random();

    public static Game.PLAYER_HAND obtainRandomHand() {
        final Game.PLAYER_HAND[] hands = Game.PLAYER_HAND.values();
        return hands[random.nextInt(hands.length)];
    }
}
