package ben.learn.console;

import ben.learn.Game;
import ben.learn.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess {

	// == fields ==
	final Game game;
	final MessageGenerator messageGenerator;

	// == constructor ==
	@Autowired
	public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator){
		this.game = game;
		this.messageGenerator = messageGenerator;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void start() {
		log.info("ContextRefreshedEvent事件");

		Scanner scanner = new Scanner(System.in);

		while(true){
			System.out.println(messageGenerator.getMainMessage());
			System.out.println(messageGenerator.getResultMessage());

			int guess = scanner.nextInt();
			scanner.nextLine();
			game.setGuess(guess);
			game.check();

			if(game.isGameLost() || game.isGameWon()){
				System.out.println(messageGenerator.getResultMessage());
				System.out.println("還要再玩嗎？(y/n)");

				String playAgainString = scanner.nextLine().trim();
				if(!playAgainString.equalsIgnoreCase("y")){
					break;
				}

				game.reset();
			}
		}
	}
}
