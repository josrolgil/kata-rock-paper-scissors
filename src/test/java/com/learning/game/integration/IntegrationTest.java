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
                .andExpect(content().string(anyOf(
                        containsString(Player.SCISSORS.toString()),
                        containsString(Player.ROCK.toString()),
                        containsString(Player.PAPER.toString()))))
                .andExpect(content().string(anyOf(
                        containsString(Result.P1.toString()),
                        containsString(Result.P2.toString()),
                        containsString(Result.DRAW.toString()))));

        //Before cleaning, it is needed to create a session for testing purposes
        final GameRound gameRound = new GameRound(Player.ROCK, Player.ROCK, Result.DRAW);
        this.mockMvc.perform(get("/game/clean")
                .sessionAttr("games", new ArrayList<>(List.of(gameRound))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Rounds: 0")))
                .andExpect(content().string(not(containsString(Player.SCISSORS.toString()))))
                .andExpect(content().string(not(containsString(Player.ROCK.toString()))))
                .andExpect(content().string(not(containsString(Player.PAPER.toString()))))
                .andExpect(content().string(not(containsString(Result.P1.toString()))))
                .andExpect(content().string(not(containsString(Result.P2.toString()))))
                .andExpect(content().string(not(containsString(Result.DRAW.toString()))));

        //Checking statistics
        final String expectedStatsRounds = "<td>Total rounds played</td>\n" +
                "                    <td>1</td>";
        final String expectedStatsP1 ="<td>Total wins for player1</td>\n" +
                "                    <td>1</td>";
        final String expectedStatsP2 ="<td>Total wins for player2</td>\n" +
                "                    <td>1</td>";
        final String expectedStatsDraw="<td>Total draws</td>\n" +
                "                    <td>1</td>";
        this.mockMvc.perform(get("/stats")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedStatsRounds)))
                .andExpect(content().string(anyOf(
                        containsString(expectedStatsP1),
                        containsString(expectedStatsP2),
                        containsString(expectedStatsDraw))));

    }
}
