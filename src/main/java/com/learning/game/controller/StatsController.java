package com.learning.game.controller;

import com.learning.game.model.Stats;
import com.learning.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping(value = "/stats")
public class StatsController {
  private static final Logger LOG = LogManager.getLogger(StatsController.class);

  @Autowired private GameService gameDal;

  @GetMapping
  public String stats(final Model model) {
    LOG.debug("Received request for stats");
    final Stats stats = gameDal.processStatistics();
    model.addAttribute("stats", stats);
    return "stats";
  }
}
