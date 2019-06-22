package ben.learn.config;

import ben.learn.qualifiers.GuessCount;
import ben.learn.qualifiers.MaxNumber;
import ben.learn.qualifiers.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "ben.learn")
public class GameConfig {

	// == fields ==
	@Value("${game.maxNumber:100}")
	int maxNumber;

	@Value("${game.minNumber:0}")
	int minNumber;

	@Value("${game.guessCount:5}")
	int guessCount;

	// == bean methods ==
	@Bean
	@MaxNumber
	public int getMaxNumber(){
		return this.maxNumber;
	}

	@Bean
	@MinNumber
	public int getMinNumber(){
		return this.minNumber;
	}

	@Bean
	@GuessCount
	public int guessCount(){
		return this.guessCount;
	}
}
