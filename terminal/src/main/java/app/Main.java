package app;

import app.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.info("Program Initiating");
        try(ConfigurableApplicationContext context =
                    new AnnotationConfigApplicationContext(AppConfig.class)){
            NumberGeneratorImpl generator = context.getBean(NumberGeneratorImpl.class);
            System.out.println(generator.next());
            GameImpl game = context.getBean(GameImpl.class);
            log.info(String.valueOf(game.getNumber()));
            MessageGenerator messageGenerator = context.getBean(MessageGeneratorImpl.class);
            log.info(messageGenerator.getMainMessage());
            log.info(messageGenerator.getResultMessage());
        }
    }
}
