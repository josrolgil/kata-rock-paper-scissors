package com.learning.game.service;

import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import com.learning.game.service.interfaces.RoundProcessor;
import net.jcip.annotations.Immutable;
import org.springframework.stereotype.Service;

@Service
@Immutable
public class RoundGenerator implements RoundProcessor {

    @Override
    public GameRound processRound() {
        final Player player1 = RandomHand.obtainRandomHand();
        final Player player2 = Player.ROCK;
        final GameReferee gameReferee = new GameReferee(player1, player2);
        final Result result = gameReferee.judge();
        return new GameRound(player1, player2, result);
    }
}
