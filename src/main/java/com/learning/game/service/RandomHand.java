package com.learning.game.service;

import com.learning.game.model.Player;
import net.jcip.annotations.ThreadSafe;

import java.util.Random;

@ThreadSafe
public final class RandomHand {
    private static final Random random = new Random();

    public static Player obtainRandomHand() {
        final Player[] hands = Player.values();
        return hands[random.nextInt(hands.length)];
    }
}
