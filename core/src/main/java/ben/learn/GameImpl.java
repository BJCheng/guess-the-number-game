package ben.learn;

import ben.learn.qualifiers.GuessCount;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;

@Slf4j
@Getter
@Component
public class GameImpl implements Game, Serializable {

    // == fields ==

    @Getter(AccessLevel.NONE)
    @Autowired
    private NumberGenerator numberGenerator;

    @Autowired
    @GuessCount
    private int guessCount;
    private int number;

    @Setter
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // == life cycles methods ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.info("遊戲物件初始化完成，目標數字為 {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in GameImpl preDestroy()");
    }

    // == public methods ==

    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess > number)
                biggest = guess - 1;
            if(guess < number)
                smallest = guess + 1;
        }
        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==
    private void checkValidNumberRange(){
        validNumberRange = (smallest <= guess) && (guess <= biggest);
    }
}
