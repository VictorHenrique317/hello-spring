package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    private int maxNumber = 50;

    @Bean
    public int maxNumber() { return maxNumber; }
}
