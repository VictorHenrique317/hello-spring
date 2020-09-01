package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageGeneratorImpl implements MessageGenerator{
    private final GameImpl game;

    @Autowired
    public MessageGeneratorImpl(GameImpl game) {
        this.game = game;
    }

    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()){
            return "You guessed it! the number was " + game.getNumber();
        }else if(game.isGameLost()){
            return "You lost. The number was " + game.getNumber();
        }else if (!game.isValidNumberRange()){
            return "Invalid number range";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess? ";
        }else{
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            return direction + "! You have " + game.getRemainingGuesses() + " guesses left";
        }
    }}
