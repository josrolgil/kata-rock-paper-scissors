package com.learning.game.controller;

import com.learning.game.model.Game;
import com.learning.game.service.GameService;
import com.learning.game.service.RandomHand;
import com.learning.game.service.Referee;
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
        final List<Game> games = new ArrayList<>();
        session.setAttribute("games", games);
        model.addAttribute("games", games);
        model.addAttribute("rounds", games.size());
        return "game";
    }

    @GetMapping("/start")
    public String start(final Model model, final HttpSession session) {
        final Game.PLAYER_HAND player1 = RandomHand.obtainRandomHand();
        final Game.PLAYER_HAND player2 = Game.PLAYER_HAND.ROCK;
        final Referee referee = new Referee(player1, player2);
        final Game game = new Game(player1, player2, referee.selectWinner());
        final List<Game> games = (List<Game>) session.getAttribute("games");
        gameService.addGame(game);
        games.add(game);
        session.setAttribute("games", games);
        model.addAttribute("rounds", games.size());
        model.addAttribute("games", games);

        return "game";
    }

    @GetMapping("/clean")
    public String clean(final Model model, final HttpSession session) {
        final List<Game> games = (List<Game>) session.getAttribute("games");
        games.clear();
        model.addAttribute("rounds", games.size());
        model.addAttribute("games", games);
        session.setAttribute("games", games);
        return "game";
    }
}

/*
package com.learning.game.controller;

import com.learning.game.model.Game;
import com.learning.game.service.RandomHand;
import com.learning.game.service.Referee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/game")
@SessionAttributes("games")
public class GameController {

    @GetMapping()
    public String landing(Model model, HttpSession session) {
        final List<Game> games = new ArrayList<>();
//        session.setAttribute("games", games);
        model.addAttribute("games", games);
        model.addAttribute("rounds", games.size());
        return "game";
    }
    @GetMapping("/start")
    public String start(Model model, HttpSession session, @ModelAttribute("games") List<Game> games){
        final Game.PLAYER_HAND player1 = RandomHand.obtainRandomHand();
        final Game.PLAYER_HAND player2 = Game.PLAYER_HAND.ROCK;
        final Referee referee = new Referee(player1, player2);
        final Game game = new Game(player1, player2, referee.selectWinner());
//        final List<Game> games = (List<Game>) session.getAttribute("games");
        games.add(game);
//        session.setAttribute("games", games);
        model.addAttribute("rounds", games.size());
        model.addAttribute("games", games);

        return "game";
    }

    @GetMapping("/clean")
    public String clean(Model model, HttpSession session){
        final List<Game> games = (List<Game>) session.getAttribute("games");
        games.clear();
        model.addAttribute("rounds", games.size());
        model.addAttribute("games",games);
        session.setAttribute("games", games);
        return "game";
    }
}


 */