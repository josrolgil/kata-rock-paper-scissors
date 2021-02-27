package com.learning.game.controller;

import com.learning.game.model.Stats;
import com.learning.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/stats")
public class StatsController {
    @Autowired
    private GameService gameDal;

    @GetMapping
    public String stats(final Model model) {
        final Stats stats = gameDal.getStats();
        model.addAttribute("stats", stats);
        return "stats";
    }
}
