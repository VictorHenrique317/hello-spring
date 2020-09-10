package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageGeneratorImpl implements MessageGenerator {
    public static final String MAIN_MESSAGE = "game.main.message";
    public static final String FIRST_GUESS = "game.first.guess";
    public static final String GUESSES_LEFT = "game.guesses.left";
    public static final String HIGHER = "game.higher";
    public static final String LOWER = "game.lower";
    public static final String WIN = "game.win";
    public static final String LOST = "game.lost";
    public static final String INVALID = "game.invalid";



    private final GameImpl game;
    private final MessageSource messageSource;

    @Autowired
    public MessageGeneratorImpl(GameImpl game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return getMessage(WIN, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOST, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return getMessage(GUESSES_LEFT, direction, game.getRemainingGuesses());
        }
    }

    private String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}


