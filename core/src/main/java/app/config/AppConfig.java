package app.config;

import app.MessageGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GameConfig.class)
@ComponentScan(basePackages = "app")
public class AppConfig {
    @Bean
    public MessageGeneratorImpl messageGenerator(){
        return new MessageGeneratorImpl();
    }
}
