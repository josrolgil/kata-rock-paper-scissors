package com.learning.game.controller;

import com.learning.game.model.GameRound;
import com.learning.game.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.learning.game.controller.ControllerPaths.CLEAN;
import static com.learning.game.controller.ControllerPaths.GAME;
import static com.learning.game.controller.ControllerPaths.START;

@Controller
@RequestMapping(value = GAME)
@SessionAttributes("games")
public class GameController {
  private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
  @Autowired private GameService gameService;

  @GetMapping()
  public String landing(final Model model, final HttpSession session) {
    LOG.debug("Received request for landing {}", session.getId());
    final List<GameRound> gameRounds = new ArrayList<>();
    session.setAttribute("gameRounds", gameRounds);
    model.addAttribute("gameRounds", gameRounds);
    model.addAttribute("roundCounter", gameRounds.size());
    return "game";
  }

  @GetMapping(START)
  public String start(final Model model, final HttpSession session) {
    LOG.debug("Received request for starting game {}", session.getId());
    final List<GameRound> games = (List<GameRound>) session.getAttribute("gameRounds");
    final GameRound newRound = gameService.playRound();
    games.add(newRound);
    session.setAttribute("gameRounds", games);
    model.addAttribute("roundCounter", games.size());
    model.addAttribute("gameRounds", games);
    return "game";
  }

  @GetMapping(CLEAN)
  public String clean(final Model model, final HttpSession session) {
    LOG.debug("Reveived request for cleaning game {}",session.getId());
    final List<GameRound> games = (List<GameRound>) session.getAttribute("gameRounds");
    games.clear();
    model.addAttribute("roundCounter", games.size());
    model.addAttribute("gameRounds", games);
    session.setAttribute("gameRounds", games);
    return "game";
  }
}
