package com.learning.game;

import com.learning.game.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameApplication {
    @Bean
    public GameService gameDal() {
        return new GameService();
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

}
