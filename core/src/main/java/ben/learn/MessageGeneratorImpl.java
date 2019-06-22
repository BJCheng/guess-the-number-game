package ben.learn;

import ben.learn.qualifiers.GuessCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == fields ==
    final Game game;
    final int guessCount;

    // == constructor ==
    public MessageGeneratorImpl(Game game, @GuessCount int guessCount){
        this.game = game;
        this.guessCount = guessCount;
    }

    @Override
    public String getMainMessage() {
        return "數字介於 " +
                game.getSmallest() +
                " 與 " +
                game.getBiggest() +
                "之間，你能在 " +
                guessCount +
                " 次內猜到嗎?";
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()) {
            return "You guessed it! The number was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You lost. The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "Invalid number range!";
        } else if (game.getRemainingGuesses() == guessCount) {
            return "What is your first guess?";
        } else {
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }

            return direction + "! You have " + game.getRemainingGuesses() + " guess left";
        }
    }

    // == Bean life cycle ==
    @PostConstruct
    public void postConstruct() {
        log.info("訊息物件初始化完成，目前數字為 " + game.getNumber());
    }
}
