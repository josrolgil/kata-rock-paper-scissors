package com.learning.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication {


//    @Bean
//    public GameService gameDal() {
//        return new GameService();
//    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

}
