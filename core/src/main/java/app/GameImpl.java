package app;

import app.config.GuessCount;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GameImpl implements Game {
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    private final NumberGenerator numberGenerator;
    @Getter private final int guessCount;
    @Getter private int number;
    @Getter @Setter private int guess;
    @Getter private int smallest;
    @Getter private int biggest;
    @Getter private int remainingGuesses;
    @Getter private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();

        guess = 0;
        remainingGuesses = guessCount;
        number = numberGenerator.next();

    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange){
            if (guess > number){
                biggest = guess - 1;
            }
            if (guess < number){
                smallest = guess + 1;
            }
            remainingGuesses--;
        }
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }
    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
