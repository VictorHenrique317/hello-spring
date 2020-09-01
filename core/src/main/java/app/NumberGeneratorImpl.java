package app;

import app.config.MaxNumber;
import app.config.MinNumber;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator{
    private final Random random= new Random();
    @Getter private final int maxNumber;
    @Getter private final int minNumber;
    private final int[] range;

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.range = new int[maxNumber - minNumber + 1];
        int counter = 0;
        for (int i = minNumber; i <= maxNumber ; i++){
            range[counter++] = i;
        }
    }

    @Override
    public int next() {
        int index = random.nextInt(range.length);
        return range[index];
    }
}
