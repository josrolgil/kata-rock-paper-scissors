package com.learning.game.integration;

import com.learning.game.model.GameRound;
import com.learning.game.model.Player;
import com.learning.game.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCompleteIntegration() throws Exception {
        //Testing landing page
        this.mockMvc.perform(get("/game")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rounds: 0")))
                .andExpect(content().string(containsString("Player 1")))
                .andExpect(content().string(containsString("Player 2")))
                .andExpect(content().string(containsString("Winner")));

        //Starting the web. Setting session with empty list
        this.mockMvc.perform(get("/game/start").sessionAttr("games", new ArrayList<>())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Rounds: 1")))
                .andExpect(content().string(anyOf(containsString("SCISSORS"), containsString("ROCK"), containsString("PAPER"))))
                .andExpect(content().string(anyOf(containsString("P1"), containsString("P2"), containsString("DRAW"))));

        //Before cleaning, it is needed to create a session for testing purposes
        final GameRound gameRound = new GameRound(Player.ROCK, Player.ROCK, Result.DRAW);
        this.mockMvc.perform(get("/game/clean").sessionAttr("games", new ArrayList<>(List.of(gameRound)))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rounds: 0")))
                .andExpect(content().string(not(containsString("SCISSORS"))))
                .andExpect(content().string(not(containsString("ROCK"))))
                .andExpect(content().string(not(containsString("PAPER"))))
                .andExpect(content().string(not(containsString("P1"))))
                .andExpect(content().string(not(containsString("P2"))))
                .andExpect(content().string(not(containsString("DRAW"))));

        //Checking statistics
        this.mockMvc.perform(get("/stats")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Total rounds played: 1")))
                .andExpect(content().string(anyOf(containsString("Total wins for player1: 1"), containsString("Total wins for player2: 1"), containsString("Total draws: 1"))));

    }
}
