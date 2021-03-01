package com.learning.game.controller;

import com.learning.game.model.GameRound;
import com.learning.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/game")
@SessionAttributes("games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping()
    public String landing(final Model model, final HttpSession session) {
        final List<GameRound> games = new ArrayList<>();
        session.setAttribute("games", games);
        model.addAttribute("games", games);
        model.addAttribute("rounds", games.size());
        return "game";
    }

    @GetMapping("/start")
    public String start(final Model model, final HttpSession session) {
        final List<GameRound> games = (List<GameRound>) session.getAttribute("games");
        final GameRound newRound = gameService.playRound();
        games.add(newRound);
        session.setAttribute("games", games);
        model.addAttribute("rounds", games.size());
        model.addAttribute("games", games);
        return "game";
    }

    @GetMapping("/clean")
    public String clean(final Model model, final HttpSession session) {
        final List<GameRound> games = (List<GameRound>) session.getAttribute("games");
        games.clear();
        model.addAttribute("rounds", games.size());
        model.addAttribute("games", games);
        session.setAttribute("games", games);
        return "game";
    }
}