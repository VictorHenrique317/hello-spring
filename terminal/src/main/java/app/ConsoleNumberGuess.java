package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess{
    private Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator messageGenerator;
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("onNumberGuess");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String answer = scanner.nextLine().trim();
                if (!answer.equalsIgnoreCase("y")) break;
                game.reset();
            }
        }
    }
}
